package com.easy.zssn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.easy.zssn.Objects.SurvivorRO;
import com.easy.zssn.Objects.TradeObject;
import com.easy.zssn.model.Inventory;
import com.easy.zssn.model.Location;
import com.easy.zssn.model.Survivor;
import com.easy.zssn.repository.SurvivorRepository;
import com.easy.zssn.service.InventoryService;
import com.easy.zssn.service.LocationService;
import com.easy.zssn.service.SurvivorService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest 
@AutoConfigureMockMvc
class ZssnApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	SurvivorService survivorService;
	@MockBean
	InventoryService inventoryService;
	@MockBean
	LocationService locationService;
	@MockBean
	SurvivorRepository survivorRepository;


	Survivor RECORD_1 = new Survivor( 1, "Fernando Miranda", 35, "M", false  );
	Survivor RECORD_2 = new Survivor( 2, "Fernando Hernandez", 34, "M" , false);
	Survivor RECORD_3 = new Survivor( 3, "Fernando Hernandez Miranda", 33, "M" , true);
	Survivor RECORD_8 = new Survivor( 8, "Erik Hernandez Miranda", 32, "M" , false);

	Inventory RECORD_4 = new Inventory(4, 1, "Water", 5);
	Inventory RECORD_5 = new Inventory(5, 2, "Food", 6);
	Inventory RECORD_6 = new Inventory(6, 3, "Medication", 7);
	Inventory RECORD_7 = new Inventory(7, 8, "Ammunition", 8);

	Location RECORD_9 = new Location(1, 17.082521, -96.699069 );	

	@Test
	public void reports() throws Exception{
		List<Survivor> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_8));
		Mockito.when(survivorService.findAllSurvivor()).thenReturn(records);
		List<Inventory> listaAux1 = new ArrayList<>(Arrays.asList(RECORD_4));
		List<Inventory> listaAux2 = new ArrayList<>(Arrays.asList(RECORD_5));
		List<Inventory> listaAux3 = new ArrayList<>(Arrays.asList(RECORD_6));
		List<Inventory> listaAux4 = new ArrayList<>(Arrays.asList(RECORD_7));
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_1.getId())).thenReturn(listaAux1);
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_2.getId())).thenReturn(listaAux2);
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_3.getId())).thenReturn(listaAux3);
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_8.getId())).thenReturn(listaAux4);
		


		mockMvc.perform(MockMvcRequestBuilders.get("/survivor/reports")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.food_per_Survivor", Matchers.is(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.medication_per_Survivor", Matchers.is(0)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.water_per_Survivor", Matchers.is(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.infected_Survivors", Matchers.is(25.0)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.ammunition_per_Survivor", Matchers.is(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.points_Lost_Infected", Matchers.is(14)))
			.andExpect(MockMvcResultMatchers.jsonPath("$.not_Infected_Survivors", Matchers.is(75.0)))
			;
	}

	@Test
	public void trade() throws Exception{
		List<Survivor> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_8));
		Mockito.when(survivorService.findAllSurvivor()).thenReturn(records);
		List<Inventory> listaAux1 = new ArrayList<>(Arrays.asList(RECORD_4));
		List<Inventory> listaAux2 = new ArrayList<>(Arrays.asList(RECORD_5));
		List<Inventory> listaAux3 = new ArrayList<>(Arrays.asList(RECORD_6));
		List<Inventory> listaAux4 = new ArrayList<>(Arrays.asList(RECORD_7));
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_1.getId())).thenReturn(listaAux1);
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_2.getId())).thenReturn(listaAux2);
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_3.getId())).thenReturn(listaAux3);
		Mockito.when(inventoryService.findAllBySurvivorID(RECORD_8.getId())).thenReturn(listaAux4);

		TradeObject trade = new TradeObject();
		trade.setSurvivorID1(RECORD_1.getId());
		trade.setSurvivorID2(RECORD_8.getId());
		trade.setInventory1(new Inventory[]{ new Inventory(0, RECORD_1.getId(), "Water", 1) } );
		trade.setInventory2(new Inventory[]{ new Inventory(0, RECORD_8.getId(), "Ammunition", 4) } );

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/survivor/trade")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(
					trade
				));
	
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is("trade done!!")));
	}

	@Test
	public void getSurvivors() throws Exception{
		List<Survivor> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_8));
		Mockito.when(survivorService.findAllSurvivor()).thenReturn(records);
		mockMvc.perform(MockMvcRequestBuilders.get("/survivor/")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Fernando Hernandez")))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].age", Matchers.is(33)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].infected", Matchers.is(false)))
			;
	}

	@Test
	public void getLocations() throws Exception{
		List<Location> records = new ArrayList<>(Arrays.asList(RECORD_9));
		Mockito.when(locationService.findAllLocation()).thenReturn(records);
		//Location RECORD_9 = new Location(1, 17.082521, -96.699069 );	
		mockMvc.perform(MockMvcRequestBuilders.get("/location/")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].survivorID", Matchers.is(1)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].latitude", Matchers.is(17.082521)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].longitude", Matchers.is(-96.699069)))
			;
	}

	@Test
	public void getInventorys() throws Exception{
		List<Inventory> records = new ArrayList<>(Arrays.asList(RECORD_4, RECORD_5, RECORD_6, RECORD_7));
		Mockito.when(inventoryService.findAllInventory()).thenReturn(records);
		mockMvc.perform(MockMvcRequestBuilders.get("/inventory/")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(4)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[1].survivorID", Matchers.is(2)))
			.andExpect(MockMvcResultMatchers.jsonPath("$[2].itemName", Matchers.is("Medication")))
			.andExpect(MockMvcResultMatchers.jsonPath("$[3].itemCount", Matchers.is(8)))
			;
			/**    {
        "id": 5,
        "survivorID": 2,
        "itemName": "Water",
        "itemCount": 10
    }, */
	}


	@Test
	public void survivorUpsert() throws Exception{
		Survivor newSurvivor = new Survivor(0,"Fernando Miranda",35,"M", false);
		SurvivorRO survivorRO = new SurvivorRO();
		survivorRO.setNewSurvivor(newSurvivor);
		//Survivor RECORD_1 = new Survivor( 1, "Fernando Miranda", 35, "M", false  );
		Mockito.when(survivorRepository.save( newSurvivor )).thenReturn(RECORD_1);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/survivor/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(survivorRO));
	
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", Matchers.is("Fernando Miranda")));
	}


	@Test
	public void add() throws Exception{
		Mockito.when(survivorService.upsertSurvivor( new Survivor(0,"Erik Hernández Miranda",32,"M", false) )).thenReturn(RECORD_1);
		Mockito.when(locationService.upsertLocation( new Location(0,17.082521,-96.699069) )).thenReturn(RECORD_9);
		Mockito.when(inventoryService.upsertInventory( new Inventory(0,0,"Water",10) )).thenReturn(RECORD_4);
/**
 * Survivor:id: 0
name: Erik Hernández Miranda
age: 32
gender: M
isInfected: false

 */
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/survivor/add")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content("{"+
				"    \"newSurvivor\":{"+
				"        \"id\":0,"+
				"        \"name\":\"Erik Hernández Miranda\","+
				"        \"age\":32,"+
				"        \"gender\":\"M\","+
				"        \"isInfected\":false,"+
				"        \"reports\":0"+
				"    },"+
				"    \"newLocation\":{"+
				"        \"survivorID\":0,"+
				"        \"latitude\":\"17.082521\","+
				"        \"longitude\":\"-96.699069\""+
				"    },"+
				"    \"items\":["+
				"        { \"id\":0,"+
				"            \"survivorID\":0,"+
				"            \"itemName\":\"Water\","+
				"            \"itemCount\":10"+
				"        }"+
				"    ]"+
				"}"
				);
	
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				//.andExpect(jsonPath("$", Matchers.notNullValue()))
				.andExpect(jsonPath("$", Matchers.is("Agregado sobreviviente y sus objetos")));
	}

}
