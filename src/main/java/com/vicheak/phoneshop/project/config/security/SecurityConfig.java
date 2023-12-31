package com.vicheak.phoneshop.project.config.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vicheak.phoneshop.project.config.jwt.JwtLoginFilter;
import com.vicheak.phoneshop.project.config.jwt.TokenVerifyFilter;

import static com.vicheak.phoneshop.project.config.security.PermissionEnum.*;

@Configuration
@EnableGlobalMethodSecurity(
		prePostEnabled = true, 
		securedEnabled = true,
		jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//change from form-based to basic auth
		http.csrf().disable()
			//add the customized interceptor
			.addFilter(new JwtLoginFilter(authenticationManager()))
			.addFilterAfter(new TokenVerifyFilter(), JwtLoginFilter.class)
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests()
			.antMatchers("/", "index.html", "css/**", "js/**").permitAll()
			//.antMatchers("/models").hasRole(RoleEnum.SALE.name())
			//.antMatchers("/brands").hasRole("SALE")
			/*.antMatchers(HttpMethod.POST, "/brands").hasAuthority("brand:write")
			.antMatchers(HttpMethod.GET, "/brands").hasAuthority("brand:read")*/
			//.antMatchers(HttpMethod.POST, "/brands").hasAuthority(BRAND_WRITE.getDescription())
			//.antMatchers(HttpMethod.GET, "/brands").hasAuthority(BRAND_READ.getDescription())
			.anyRequest()
			.authenticated(); 
			//.and()
			//.httpBasic();
	
	}
	
	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		
		//User user1 = new User("dara", passwordEncoder.encode("dara123"), Collections.emptyList());
		
		UserDetails user1 = User.builder()
				.username("dara")
				.password(passwordEncoder.encode("dara123"))
				//.roles("SALE") //ROLE_SALE
				.authorities(RoleEnum.SALE.getAuthorities()) //collection of GrantedAuthority
				.build();
		
		UserDetails user2 = User.builder()
				.username("thida")
				.password(passwordEncoder.encode("thida123"))
				//.roles("ADMIN") //ROLE_ADMIN
				.authorities(RoleEnum.ADMIN.getAuthorities())
				.build();
		
		UserDetailsService userDetailsService = 
				new InMemoryUserDetailsManager(user1, user2);
		
		return userDetailsService;
	}

}
