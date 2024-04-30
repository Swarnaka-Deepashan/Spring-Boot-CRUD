package in.swarnaka.springbootcrud.service;

import java.util.List;

import in.swarnaka.springbootcrud.exception.InventoryCollectionException;
import in.swarnaka.springbootcrud.model.InventoryDTO;
import jakarta.validation.ConstraintViolationException;

public class InventoryServiceImpl implements InventoryService {

	@Override
	public void CreateInventory(InventoryDTO inventory)
			throws ConstraintViolationException, InventoryCollectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InventoryDTO> getAllInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InventoryDTO getSingleInventory(String id) throws InventoryCollectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateInventory(String id, InventoryDTO inventory) throws InventoryCollectionException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteInventoryById(String id) throws InventoryCollectionException {
		// TODO Auto-generated method stub
		
	}
	
	

}
