package in.swarnaka.springbootcrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import in.swarnaka.springbootcrud.exception.InventoryCollectionException;
import in.swarnaka.springbootcrud.model.InventoryDTO;
import jakarta.validation.ConstraintViolationException;

public interface InventoryService {
	
	public void CreateInventory(InventoryDTO inventory) throws ConstraintViolationException,InventoryCollectionException ;
	
	public List<InventoryDTO> getAllInventory();
	
	public InventoryDTO getSingleInventory(String id) throws InventoryCollectionException;
	
	public void updateInventory(String id, InventoryDTO inventory) throws InventoryCollectionException;
	
	public void deleteInventoryById(String id) throws InventoryCollectionException;

}
