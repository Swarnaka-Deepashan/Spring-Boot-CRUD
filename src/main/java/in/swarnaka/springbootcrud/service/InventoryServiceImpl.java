package in.swarnaka.springbootcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.swarnaka.springbootcrud.exception.InventoryCollectionException;
import in.swarnaka.springbootcrud.exception.ItemCollectionException;
import in.swarnaka.springbootcrud.model.InventoryDTO;
import in.swarnaka.springbootcrud.model.ItemDTO;
import in.swarnaka.springbootcrud.repository.InventoryRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class InventoryServiceImpl implements InventoryService {
	
	@Autowired
	private InventoryRepository inventoryRepository;

	@Override
	public void CreateInventory(InventoryDTO inventory) throws ConstraintViolationException, InventoryCollectionException {
		
		Optional<InventoryDTO> inventoryOptional = inventoryRepository.findByName(inventory.getBatchNumber());
		if (inventoryOptional.isPresent()) {
			throw new InventoryCollectionException(InventoryCollectionException.InventoryAllreadyExists());
		}else {
			inventoryRepository.save(inventory);
		}
		
	}

	@Override
	public List<InventoryDTO> getAllInventory() {
		List<InventoryDTO> inventory = inventoryRepository.findAll();
		if (inventory.size()>0) {
			return inventory;		
		}else {
			return new ArrayList<InventoryDTO>();
		}
	}

	@Override
	public InventoryDTO getSingleInventory(String id) throws InventoryCollectionException {
		Optional<InventoryDTO> optionalInventory = inventoryRepository.findById(id);
		if(!optionalInventory.isPresent()) {
			throw new InventoryCollectionException(InventoryCollectionException.NotFoundException(id));
		}else {
			return optionalInventory.get();
		}
	}

	@Override
	public void updateInventory(String id, InventoryDTO inventory) throws InventoryCollectionException {
		Optional<InventoryDTO> inventoryWithId = inventoryRepository.findById(id);
		Optional<InventoryDTO> inventoryWithSameName = inventoryRepository.findByName(inventory.getBatchNumber());
		
		if (inventoryWithId.isPresent()) {
			
			InventoryDTO inventoryToUpdate =  inventoryWithId.get();
			inventoryToUpdate.setItemId(inventory.getItemId());
			inventoryToUpdate.setPurchasePrice(inventory.getPurchasePrice());
			inventoryToUpdate.setSalesPrice(inventory.getSalesPrice());
			inventoryToUpdate.setBatchNumber(inventory.getBatchNumber());
			inventoryToUpdate.setExpiryDate(inventory.getExpiryDate());
			inventoryToUpdate.setStock(inventory.getStock());
			
			inventoryRepository.save(inventoryToUpdate);
		}else {
			throw new InventoryCollectionException(ItemCollectionException.NotFoundException(id));
		}
		
	}

	@Override
	public void deleteInventoryById(String id) throws InventoryCollectionException {
		Optional<InventoryDTO> inventoryOptional = inventoryRepository.findById(id);
		if (!inventoryOptional.isPresent()) {
			throw new InventoryCollectionException(InventoryCollectionException.NotFoundException(id));
		}else {
			inventoryRepository.deleteById(id);
		}
		
	}
	
	@Override
    public List<InventoryDTO> getInventoriesByItemId(String itemId) {
        return inventoryRepository.findByItemId(itemId);
    }
	
	
	
	

}
