package in.swarnaka.springbootcrud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.swarnaka.springbootcrud.model.InventoryDTO;
import in.swarnaka.springbootcrud.model.ItemDTO;

@Repository
public interface InventoryRepository extends MongoRepository<InventoryDTO, String> {
	
	List<InventoryDTO> findByItemId(String itemId);
	
	@Query("{'inventory': ?0}")
	Optional<InventoryDTO> findByName(String Inventory);
	
	

}
