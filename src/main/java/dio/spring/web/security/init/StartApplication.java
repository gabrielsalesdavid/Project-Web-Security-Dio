package dio.spring.web.security.init;

import dio.spring.web.security.model.User;
import dio.spring.web.security.repository.IUserRepository;

public class StartApplication {
	
	private IUserRepository repository;
	
	public void run(String... args) throws Exception {
		
		User user = repository.findByUsername("admin");
		
		if (user == null) {
			
			user = new User();
			user.setName("ADMIN");
			user.setUsername("admin");
			user.setPassword("master123");
			user.getRoles().add("MANAGERS");
			repository.save(user);
		}
		
		user = repository.findByUsername("user");
		
		if(user == null) {
			
			user = new User();
			user.setName("USER");
			user.setUsername("user");
			user.setPassword("user123");
			user.getRoles().add("USERS");
			repository.save(user);
		}
	}
}