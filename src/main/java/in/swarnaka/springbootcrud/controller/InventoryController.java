package in.swarnaka.springbootcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.swarnaka.springbootcrud.exception.InventoryCollectionException;
import in.swarnaka.springbootcrud.model.InventoryDTO;
import in.swarnaka.springbootcrud.service.InventoryService;
import jakarta.validation.ConstraintViolationException;

@RestController
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	@GetMapping("/inventory")
	public ResponseEntity<?> getAllInventory(){
		List<InventoryDTO> inventory = inventoryService.getAllInventory(); 
		return new ResponseEntity<>(inventory, inventory.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/inventory")
	public ResponseEntity<?> createinventory(@RequestBody InventoryDTO inventory){
		try {
			inventoryService.CreateInventory(inventory);
			return new ResponseEntity<InventoryDTO>(inventory,HttpStatus.OK );
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (InventoryCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	@GetMapping("/inventory/{id}")
	public ResponseEntity<?> getSingleInventory(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(inventoryService.getSingleInventory(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	

}
