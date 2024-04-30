package in.swarnaka.springbootcrud.service;

import java.util.List;

import in.swarnaka.springbootcrud.exception.ItemCollectionException;
import in.swarnaka.springbootcrud.model.ItemDTO;
import jakarta.validation.ConstraintViolationException;

public interface ItemService {
	
	public void CreateItem(ItemDTO item) throws ConstraintViolationException,ItemCollectionException ;
	
	public List<ItemDTO> getAllItems();
	
	public ItemDTO getSingleItem(String id) throws ItemCollectionException;
	
	public void updateItem(String id, ItemDTO item) throws ItemCollectionException;
	
	public void deleteItemById(String id) throws ItemCollectionException;
		
}
