package dio.spring.web.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dio.spring.web.security.model.User;
import dio.spring.web.security.repository.IUserRepository;

@Service
public class SecurityDatabaseService implements UserDetailsService {

	@Autowired
	private IUserRepository iuserRepos;

	@Override
	public UserDetails loadUserByUsername(String username) {
		
		User userEntity = iuserRepos.findByUsername(username);
		
		if (userEntity == null) {
			
			throw new UsernameNotFoundException(username);
		}
		
		Set<GrantedAuthority> authorities = new HashSet<>();
		userEntity.getRoles().forEach(role -> {
			authorities.add(new SimpleGrantedAuthoroty("ROLE_" + role));
		});
		
		UserDetails user = new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
				userEntity,getPassword(),
				authorities);	
		
		return user;
	}
}