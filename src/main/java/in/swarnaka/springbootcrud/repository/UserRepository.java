package in.swarnaka.springbootcrud.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import in.swarnaka.springbootcrud.model.User;


public interface UserRepository extends MongoRepository<User, String> {
	
	 Optional<User> findByUsername(String username);
	
}
