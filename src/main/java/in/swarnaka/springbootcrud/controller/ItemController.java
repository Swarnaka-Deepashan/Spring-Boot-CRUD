package in.swarnaka.springbootcrud.controller;

import java.util.List;
import java.util.Optional;

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

import in.swarnaka.springbootcrud.exception.ItemCollectionException;
import in.swarnaka.springbootcrud.model.InventoryDTO;
import in.swarnaka.springbootcrud.model.ItemDTO;
import in.swarnaka.springbootcrud.repository.ItemRepository;
import in.swarnaka.springbootcrud.service.InventoryService;
import in.swarnaka.springbootcrud.service.ItemService;
import jakarta.validation.ConstraintViolationException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private InventoryService inventoryService;
	
	
	@GetMapping("/items")
	public ResponseEntity<?> getAllItems(){
		List<ItemDTO> items = itemService.getAllItems(); 
		return new ResponseEntity<>(items, items.size()>0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/items")
	public ResponseEntity<?> createItem(@RequestBody ItemDTO item){
		try {
			itemService.CreateItem(item);
			return new ResponseEntity<ItemDTO>(item,HttpStatus.OK );
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (ItemCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}
	}
	
	
	@GetMapping("/items/{id}")
	public ResponseEntity<?> getSingleItem(@PathVariable("id") String id){
		try {
			return new ResponseEntity<>(itemService.getSingleItem(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	///////////////////////////////////////////
	
	@GetMapping("/inventory/item/{itemId}")
	public ResponseEntity<List<InventoryDTO>> getInventoriesByItemId(@PathVariable String itemId) {
	    List<InventoryDTO> inventories = inventoryService.getInventoriesByItemId(itemId);
	    return new ResponseEntity<>(inventories, HttpStatus.OK);
	}

	
	////////////////////////////////////////////
	
	
	@PutMapping("/items/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody ItemDTO item){
		try {
			itemService.updateItem(id, item);
			return ResponseEntity.noContent().build();  // Return 204 No Content
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch(ItemCollectionException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	
	@DeleteMapping("/items/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
	    try {
	        itemService.deleteItemById(id);
	        return ResponseEntity.noContent().build();  // Return 204 No Content
	    } catch (ItemCollectionException e) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	
	
	
}
