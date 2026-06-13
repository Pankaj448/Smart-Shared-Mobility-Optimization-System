package tech.codehunt.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import tech.codehunt.dao.AdminDao;
import tech.codehunt.model.Admin;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
        private final AdminDao adminDao;
        private final PasswordEncoder passwordEncoder;
        private final String defaultUsername;
        private final String defaultPassword;

		public UserDetailsServiceImpl(AdminDao adminDao,
									  PasswordEncoder passwordEncoder,
									  @Value("${app.admin.default-username:admin}") String defaultUsername,
									  @Value("${app.admin.default-password:admin123}") String defaultPassword) {
			this.adminDao = adminDao;
			this.passwordEncoder = passwordEncoder;
			this.defaultUsername = defaultUsername;
			this.defaultPassword = defaultPassword;
		}

		@PostConstruct
        public void init() {
        	      if(adminDao.findByUsername(defaultUsername).isEmpty()) {
        	    	  
        	    	  Admin admin=new Admin();
        	    	  admin.setUsername(defaultUsername);
        	    	  admin.setPassword(passwordEncoder.encode(defaultPassword));
        	    	  adminDao.save(admin);
        	      }
        }

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> byUsername = adminDao.findByUsername(username);
		Admin admin = byUsername.orElseThrow(() -> new UsernameNotFoundException("Admin Does Not Exist"));
          
		 return User.withUsername(admin.getUsername())
				 .password(admin.getPassword())
				 .roles("ADMIN")
				 .build();
	}
	
 
}
