package com.easy.zssn.controller;

import java.util.List;

import com.easy.zssn.Objects.ItemPoints;
import com.easy.zssn.Objects.ReportSurvivor;
import com.easy.zssn.Objects.SurvivorRO;
import com.easy.zssn.Objects.TradeObject;
import com.easy.zssn.model.Inventory;
import com.easy.zssn.model.Location;
import com.easy.zssn.model.Survivor;
import com.easy.zssn.service.InventoryService;
import com.easy.zssn.service.LocationService;
import com.easy.zssn.service.SurvivorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping(path="/survivor")
public class SurvivorController {
    @Autowired
    public SurvivorService survivorService;

    @Autowired
    public LocationService locationService;

    @Autowired
    public InventoryService inventoryService;
    
    /*
        CRUD Survivor
     */
    @PostMapping
    public @ResponseBody Survivor upsertSurvivor(
        @RequestBody SurvivorRO newObj
    ){
        return survivorService.upsertSurvivor(newObj.getNewSurvivor());
    }

    @DeleteMapping
    public @ResponseBody String deleteSurvivor(
        @RequestParam int id
    ){  String resultado = "";
        if(survivorService.deleteSurvivor(id))
            resultado = "Survivor deleted correctly";
        else
            resultado = "Error trying to delete Survivor";
        return resultado;
    }

    @GetMapping(value="/findbyid")
    public @ResponseBody Survivor findbySurvivor(
        @RequestParam int id
    ){
        return survivorService.findByIDSurvivor(id);
    }

    @GetMapping
    public @ResponseBody List<Survivor> findall(
    ){
        return survivorService.findAllSurvivor();
    }

    
    //------------------------ Alta completa del survivor
    @PostMapping(path="/add")
    public @ResponseBody String agregarSurvivor(
        @RequestBody SurvivorRO newObj
    ){
        String resultado = "";
        System.out.println("\n\n\n\n\n\n\n"+newObj.toString());
        Survivor newSurvivor1 = survivorService.upsertSurvivor(newObj.getNewSurvivor());
        Location newLocation1 = newObj.getNewLocation();
        newLocation1.setSurvivorID(newSurvivor1.getId());
        locationService.upsertLocation(newLocation1);
        Inventory[] items = newObj.getItems();

        for(int i = 0 ; i < items.length ; i++ ){
            Inventory inv= new Inventory();
            inv.setItemCount(items[i].getItemCount());
            inv.setItemName(items[i].getItemName());
            inv.setSurvivorID(newSurvivor1.getId());
            inventoryService.upsertInventory(inv);
        }
        resultado = "Agregado sobreviviente y sus objetos";
        return resultado;
    }
    

    @PostMapping(path="/reportInfected")
    public @ResponseBody String reportInfected(
        @RequestParam Integer survivorID,
        @RequestParam Integer survivorIDReported
    ){
        Survivor survivorResponse = survivorService.findByIDSurvivor(survivorID);
        if( survivorResponse  == null ){
            return "survivor not found!!";
        }
        Survivor survivorReportedResponse = survivorService.findByIDSurvivor(survivorIDReported);
        Survivor survivor = survivorReportedResponse;
        survivor.addReport();
        survivorService.upsertSurvivor(survivor);
        return "Survivor Reported!!";
    }

    @PostMapping(path="/trade")
    public @ResponseBody String trade(
        @RequestBody TradeObject trade
    ){
        //validamos que el survivor 1 tenga lo suficiente para negociar
        List<Inventory> lista1 = inventoryService.findAllBySurvivorID(trade.getSurvivorID1());
        Inventory differenceleft1 = null ;
        int points1 =0;
        for( int i=0; i < trade.getInventory1().length ; i++ ){
            differenceleft1 = getDifferenceItems( lista1, trade.getInventory1()[i]);
            if( differenceleft1.getItemCount() < 0){
                return "Not enougth "+trade.getInventory1()[i].getItemName()+" to trade.";
            }
            points1 += getPoints(trade.getInventory1()[i]);
        }

        //validamos que el survivor 2 tenga lo suficiente para negociar
        List<Inventory> lista2 = inventoryService.findAllBySurvivorID(trade.getSurvivorID2());
        Inventory differenceleft2 = null ;
        int points2 = 0;
        for( int i=0; i < trade.getInventory2().length ; i++ ){
            differenceleft2 = getDifferenceItems( lista2, trade.getInventory2()[i]);
            if( differenceleft2.getItemCount() < 0){
                return "Not enougth "+trade.getInventory2()[i].getItemName()+" to trade.";
            }
            points2 += getPoints(trade.getInventory2()[i]);
        }

        if(points1 != points2)
            return "trade is not fair!! Points in Survivor 1 "+points1+" Survivor2 "+points2;

        for( int i=0; i < trade.getInventory1().length ; i++ ){
            for( Inventory invaux : lista2 ){
                if(  invaux.getItemName().toLowerCase().contentEquals( trade.getInventory1()[i].getItemName().toLowerCase() )  ){
                    invaux.setItemCount( invaux.getItemCount() + trade.getInventory1()[i].getItemCount() ) ;
                    break;
                }
            }
        }
        
        for( int i=0; i < trade.getInventory2().length ; i++ ){
            for( Inventory invaux : lista1 ){
                if(  invaux.getItemName().toLowerCase().contentEquals( trade.getInventory2()[i].getItemName().toLowerCase() )  ){
                    invaux.setItemCount( invaux.getItemCount() + trade.getInventory2()[i].getItemCount() ) ;
                    break;
                }
            }
        }
        
        //Actualizamos los datos requeridos
        inventoryService.upsertInventory(differenceleft1);
        //inventoryService.upsertInventory(differenceleft2);
        return "trade done!!";
    }

    @GetMapping(path="/reports")
    public @ResponseBody ReportSurvivor reports(){
        ReportSurvivor result= new ReportSurvivor();
        int cantidad_no_infectados=0;
        int cantidad_infectados=0;
        int puntos_perdidos=0;
        int Water_Total=0;
        int Food_Total=0;
        int Medication_Total=0;
        int Ammunition_Total=0;

        List<Survivor> list = survivorService.findAllSurvivor();
        for( Survivor aux : list ){
            List<Inventory> listaAux = inventoryService.findAllBySurvivorID(aux.getId());
            if(aux.isInfected()){
                cantidad_infectados++;
                for( Inventory invaux : listaAux ){
                    puntos_perdidos = puntos_perdidos + getPoints(invaux);
                }
            }
            else{
                cantidad_no_infectados++;
                for( Inventory invaux : listaAux ){
                    switch ( invaux.getItemName().toLowerCase() ) {
                        case "water":
                            Water_Total= Water_Total + invaux.getItemCount();
                            break;
                        case "food":
                            Food_Total= Food_Total + invaux.getItemCount();
                            break;
                        case "medication":
                            Medication_Total= Medication_Total + invaux.getItemCount();
                            break;
                        case "ammunition":
                            Ammunition_Total= Ammunition_Total + invaux.getItemCount();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        if(list.size()>0){
            result.setInfected_Survivors( (100*(float)cantidad_infectados/list.size()) );
            result.setNot_Infected_Survivors( (100*(float)cantidad_no_infectados/list.size()));
            result.setAmmunition_per_Survivor(Ammunition_Total/cantidad_no_infectados);
            result.setFood_per_Survivor(Food_Total/cantidad_no_infectados);
            result.setMedication_per_Survivor(Medication_Total/cantidad_no_infectados);
            result.setWater_per_Survivor(Water_Total/cantidad_no_infectados);
            result.setPoints_Lost_Infected(puntos_perdidos);
        }
        else{
            result.setInfected_Survivors( 0 );
            result.setNot_Infected_Survivors( 0 );
            result.setAmmunition_per_Survivor(0);
            result.setFood_per_Survivor(0);
            result.setMedication_per_Survivor(0);
            result.setWater_per_Survivor(0);
            result.setPoints_Lost_Infected(0);
        }
        return result;
    }


    private Inventory getDifferenceItems(List<Inventory> lista, Inventory producto){
        Inventory resultado = new Inventory();
        boolean flag = false;
        for( Inventory invaux : lista ){
            if(  invaux.getItemName().toLowerCase().contentEquals( producto.getItemName().toLowerCase() )  ){
                resultado = invaux;
                resultado.setItemCount( invaux.getItemCount() - producto.getItemCount() ) ;
                flag = true;
                break;
            }
        }
        if( !flag )
            resultado.setItemCount(-1);
        return resultado;
    }

    private int getPoints( Inventory inventory ){
        int resultado = 0;
        switch (inventory.getItemName().toLowerCase()) {
            case "water":
                resultado = ItemPoints.Water.getValue()*inventory.getItemCount();
                break;
            case "food":
                resultado = ItemPoints.Food.getValue()*inventory.getItemCount();
                break;
            case "medication":
                resultado = ItemPoints.Medication.getValue()*inventory.getItemCount();
                break;
            case "ammunition":
                resultado = ItemPoints.Ammunition.getValue()*inventory.getItemCount();
                break;
            default:
                break;
        }
        return resultado;
    }
}
