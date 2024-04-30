package in.swarnaka.springbootcrud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.swarnaka.springbootcrud.model.InventoryDTO;

public interface InventoryRepository extends MongoRepository<InventoryDTO, String> {
	
	

}
