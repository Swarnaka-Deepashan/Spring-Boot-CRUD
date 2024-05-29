package in.swarnaka.springbootcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.swarnaka.springbootcrud.exception.InventoryCollectionException;
import in.swarnaka.springbootcrud.exception.ItemCollectionException;
import in.swarnaka.springbootcrud.model.InventoryDTO;
import in.swarnaka.springbootcrud.model.ItemDTO;
import in.swarnaka.springbootcrud.service.InventoryService;
import jakarta.validation.ConstraintViolationException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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
	
	@PutMapping("/inventory/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody InventoryDTO inventory){
		try {
			inventoryService.updateInventory(id, inventory);
			return ResponseEntity.noContent().build();  // Return 204 No Content
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch(InventoryCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@DeleteMapping("/inventory/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
	    try {
	    	inventoryService.deleteInventoryById(id);
	        return ResponseEntity.noContent().build();  // Return 204 No Content
	    } catch (InventoryCollectionException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	

}
