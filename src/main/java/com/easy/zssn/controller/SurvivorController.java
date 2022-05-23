package com.easy.zssn.controller;

import java.util.List;

import com.easy.zssn.Objects.ItemPoints;
import com.easy.zssn.Objects.ReportSurvivor;
import com.easy.zssn.Objects.SurvivorRO;
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
    @PostMapping(value="/")
    public @ResponseBody Survivor upsertSurvivor(
        @RequestBody SurvivorRO newObj
    ){
        return survivorService.upsertSurvivor(newObj.getNewSurvivor());
    }

    @DeleteMapping(value="/")
    public @ResponseBody String deleteSurvivor(
        @RequestParam int id
    ){  String resultado = "";
        if(survivorService.deleteSurvivor(id))
            resultado = "Survivor deleted correctly";
        else
            resultado = "Error trying to delete Survivor";
        return resultado;
    }

    @GetMapping(value="/findbyidSurvivor")
    public @ResponseBody Survivor findbySurvivor(
        @RequestParam int id
    ){
        return survivorService.findByIDSurvivor(id);
    }

    @GetMapping(value="/")
    public @ResponseBody List<Survivor> findall(
    ){
        return survivorService.findAllSurvivor();
    }

    
    //------------------------ Alta completa del survivor
    @PostMapping(value="/add")
    public String agregarSurvivor(
        @RequestBody SurvivorRO newObj
    ){
        String resultado = "";
        Survivor newSurvivor = survivorService.upsertSurvivor(newObj.getNewSurvivor());
        Location newLocation = newObj.getNewLocation();
        newLocation.setSurvivorID(newSurvivor.getId());
        locationService.upsertLocation(newLocation);
        Inventory[] items = newObj.getItems();

        for(int i = 0 ; i < items.length ; i++ ){
            Inventory inv= new Inventory();
            inv.setItemCount(items[i].getItemCount());
            inv.setItemName(items[i].getItemName());
            inv.setSurvivorID(newSurvivor.getId());
            inventoryService.upsertInventory(inv);
        }

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
        @RequestBody int survivorID1,
        @RequestBody Inventory[] inventory1,
        @RequestBody int survivorID2,
        @RequestBody Inventory[] inventory2
    ){
        //validamos que el survivor 1 tenga lo suficiente para negociar
        List<Inventory> lista1 = inventoryService.findAllBySurvivorID(survivorID1);
        Inventory differenceleft1 ;
        int points1 =0;
        for( int i=0; i < inventory1.length ; i++ ){
            differenceleft1 = getDifferenceItems( lista1, inventory1[i]);
            if( differenceleft1.getItemCount() < 0){
                return "Not enougth "+inventory1[i].getItemName()+" to trade.";
            }
            points1 += getPoints(inventory1[i]);
        }

        //validamos que el survivor 2 tenga lo suficiente para negociar
        List<Inventory> lista2 = inventoryService.findAllBySurvivorID(survivorID2);
        Inventory differenceleft2 ;
        int points2 = 0;
        for( int i=0; i < inventory2.length ; i++ ){
            differenceleft2 = getDifferenceItems( lista2, inventory2[i]);
            if( differenceleft2.getItemCount() < 0){
                return "Not enougth "+inventory2[i].getItemName()+" to trade.";
            }
            points2 += getPoints(inventory2[i]);
        }

        if(points1 != points2)
            return "trade is not fair!!";

        return "trade done!!";
    }

    @GetMapping(path="/reports")
    public @ResponseBody ReportSurvivor reports(){
        ReportSurvivor result= new ReportSurvivor();

        //result.set
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
