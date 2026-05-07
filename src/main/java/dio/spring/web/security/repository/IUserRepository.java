package dio.spring.web.security.repository;

import dio.spring.web.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.username = (:username")
	public User findByUsername(@Param ("username") String username);
}