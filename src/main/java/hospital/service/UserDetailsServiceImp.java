package hospital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.UserDAO;
import hospital.entity.User;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
	
	@Autowired
	private UserDAO userDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userDao.findUserByUsername(email);
		
	    UserBuilder builder = null;
	    
	    if (user != null) {
	    	builder = org.springframework.security.core.userdetails.User.withUsername(email);
	        builder.disabled(!user.isEnabled());
	        builder.password(user.getPassword());
	        String[] authorities = user.getAuthorities()
	            .stream().map(a -> a.getAuthority()).toArray(String[]::new);
	        
	        builder.authorities(authorities);
	    }else {
	    	throw new UsernameNotFoundException("User not found.");
	    }
	    
	    return builder.build();
	}

}
