package in.swarnaka.springbootcrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.swarnaka.springbootcrud.model.User;
import in.swarnaka.springbootcrud.repository.UserRepository;




@Service
public class UserService {
	
	@Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
    	
        Optional<User> user = userRepository.findByUsername(username);
        
        return user.isPresent() && user.get().getPassword().equals(password);
    }
    
    public User createUser(String username, String password) {	
    	
        User newUser = new User();
        
        newUser.setUsername(username);
        
        newUser.setPassword(password); 
        
        return userRepository.save(newUser);
    }

}


