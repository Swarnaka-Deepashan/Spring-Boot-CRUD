package in.swarnaka.springbootcrud.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.swarnaka.springbootcrud.model.ItemDTO;

@Repository
public interface ItemRepository extends MongoRepository<ItemDTO, String> {
	
	@Query("{'item': ?0}")
	Optional<ItemDTO> findByName(String Item);	
}




