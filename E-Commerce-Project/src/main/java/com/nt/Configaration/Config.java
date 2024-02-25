package com.nt.Configaration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.nt.Service.CustomUserDetailsService;


@Configuration
@EnableWebSecurity
public class Config {
	@Autowired
	GoogleoAuth2Susseshandeler GoogleoAuth2Susseshandeler;
     @Autowired
	CustomUserDetailsService CustomUserDetailsService;
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http
	            .authorizeHttpRequests()
	            .requestMatchers("/","/shop/**","/register/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .permitAll()
	            .failureUrl("/login?error=true")
	            .defaultSuccessUrl("/")
	            .usernameParameter("email")
	            .passwordParameter("password")
	            .and()
	            .oauth2Login()
	            .loginPage("/login")
	            .successHandler(GoogleoAuth2Susseshandeler)
	            .and()
	            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")
	            .and()
	            .exceptionHandling()
	            .and().csrf().disable();
	        
	        return http.build();
	     
	    }  
	 @Bean
	 public BCryptPasswordEncoder bCryptpasswordEncoder() {
		 return new BCryptPasswordEncoder();
	 }
	 
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth .userDetailsService(CustomUserDetailsService);
	    }
	 public void configure(WebSecurity web) {
	        web.ignoring().requestMatchers("/resoures/**", "/static/**","/images/**","/productImages/**");
	    }

}
