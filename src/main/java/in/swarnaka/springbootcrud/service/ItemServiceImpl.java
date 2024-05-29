package in.swarnaka.springbootcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.swarnaka.springbootcrud.exception.ItemCollectionException;
import in.swarnaka.springbootcrud.model.ItemDTO;
import in.swarnaka.springbootcrud.repository.ItemRepository;
import jakarta.validation.ConstraintViolationException;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
		public void CreateItem(ItemDTO item) throws ConstraintViolationException,ItemCollectionException {
			Optional<ItemDTO> itemOptional = itemRepository.findByName(item.getName());
			if (itemOptional.isPresent()) {
				throw new ItemCollectionException(ItemCollectionException.ItemAllreadyExists());
			}else {
				itemRepository.save(item);
			}
		}

	@Override
	public List<ItemDTO> getAllItems() {
		List<ItemDTO> items = itemRepository.findAll();
		if (items.size()>0) {
			return items;		
		}else {
			return new ArrayList<ItemDTO>();
		}
	}

	
	
	@Override
	public ItemDTO getSingleItem(String id) throws ItemCollectionException {
		Optional<ItemDTO> optionalItem = itemRepository.findById(id);
		if(!optionalItem.isPresent()) {
			throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
		}else {
			return optionalItem.get();
		}
	}

	@Override
	public void updateItem(String id, ItemDTO item) throws ItemCollectionException {
		
		Optional<ItemDTO> itemWithId = itemRepository.findById(id);
		Optional<ItemDTO> itemWithSameName = itemRepository.findByName(item.getName());
		
		if (itemWithId.isPresent()) {
			
//			if (itemWithSameName.isEmpty()) {
//				throw new ItemCollectionException(ItemCollectionException.ItemAllreadyExists());
//			}
			ItemDTO itemToUpdate =  itemWithId.get();
			itemToUpdate.setName(item.getName());
			itemToUpdate.setCode(item.getCode());
			itemToUpdate.setCategory(item.getCategory());
			itemToUpdate.setStatus(item.getStatus());
			itemRepository.save(itemToUpdate);
		}else {
			throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
		}
		
		
	}

	@Override
	public void deleteItemById(String id) throws ItemCollectionException {
		Optional<ItemDTO> itemOptional = itemRepository.findById(id);
		if (!itemOptional.isPresent()) {
			throw new ItemCollectionException(ItemCollectionException.NotFoundException(id));
		}else {
			itemRepository.deleteById(id);
		}
	}
	
	
}
