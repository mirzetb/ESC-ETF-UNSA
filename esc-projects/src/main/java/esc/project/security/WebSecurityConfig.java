package esc.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
 
	 
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
	  	  
    
	http.csrf().disable().authorizeRequests()    	
    	.antMatchers("/css/**", "/js/**", "/images/**").permitAll()
        .antMatchers("/").permitAll()                
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.GET, "/logoutUser").permitAll()
        .antMatchers("/adminPanel").hasAuthority("SUPER_USER")
        .anyRequest().authenticated()       
        .and()                 
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
		
		
        
  }

}

