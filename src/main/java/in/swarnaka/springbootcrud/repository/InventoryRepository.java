package in.swarnaka.springbootcrud.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import in.swarnaka.springbootcrud.model.InventoryDTO;
import in.swarnaka.springbootcrud.model.ItemDTO;

public interface InventoryRepository extends MongoRepository<InventoryDTO, String> {
	
	@Query("{'inventory': ?0}")
	Optional<InventoryDTO> findByName(String Inventory);
	
	

}
