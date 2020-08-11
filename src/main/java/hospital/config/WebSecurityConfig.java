package hospital.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import hospital.filter.MyWebFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("userDetailsService")
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		.addFilterAfter(new MyWebFilter(), BasicAuthenticationFilter.class)
		http
		.authorizeRequests()
		.antMatchers("/hospital_rooms/**").hasAnyRole("ADMIN", "DOCTOR")
		.antMatchers("/").permitAll()
		.and()
		.formLogin()
		.loginPage("/users/login")
		.loginProcessingUrl("/authenticateUser")
		.defaultSuccessUrl("/",true)
		.failureUrl("/users/login?error=true")
//		.failureHandler(customAuthenticationFailureHandler())
		.permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("users/login?logout")
		.logoutUrl("/logoutUser")
		.logoutSuccessUrl("/")
		.deleteCookies("JSESSIONID")
		.permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/access_denied")
		.and()
		.csrf().disable();
	}
}
