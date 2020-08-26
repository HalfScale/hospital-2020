package hospital.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hospital.dao.DoctorCodeDAO;
import hospital.dao.UserDAO;
import hospital.entity.DoctorCode;
import hospital.entity.User;

@Service("userDetailsService")
public class UserDetailsServiceImp implements UserDetailsService {
	
	private static final Logger logger = LogManager.getLogger(UserDetailsServiceImp.class);
	
	@Autowired
	private UserDAO userDao;
	
	@Autowired
	private DoctorCodeDAO doctorCodeDao;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userDao.findUserByEmail(email);
		logger.info("User value " + email);
	    UserBuilder builder = null;
	    
	    if (user != null) {
	    	
	    	//Check if user is doctor
	    	Integer doctorId = user.getUserDetail().getDoctorCodeId();
	    	DoctorCode doctorCode = doctorCodeDao.getDoctorCode(doctorId);
	    	
	    	if(doctorId != null && doctorCode == null) {
	    		//Throw error if existing doctor code does not exist or deleted.
	    		throw new UsernameNotFoundException("User not found.");
	    	}
	    	
	    	
	    	builder = org.springframework.security.core.userdetails.User.withUsername(email);
	        builder.disabled(!user.isEnabled());
	        builder.password(user.getPassword());
	        String[] authorities = user.getAuthorities()
	            .stream().map(a -> a.getAuthority()).toArray(String[]::new);
	        
	        builder.authorities(authorities);
	    }else {
	    	throw new UsernameNotFoundException("User not found.");
	    }
	    
	    logger.info("authorities " + user.getAuthorities());
	    
	    return user;
	}

}
