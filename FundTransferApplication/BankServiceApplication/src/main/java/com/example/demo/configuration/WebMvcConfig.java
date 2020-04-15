/*
 * package com.example.demo.configuration;
 * 
 * import java.util.ArrayList; import java.util.List;
 * 
 * import javax.annotation.Resource;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.annotation.Order; import
 * org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.security.provisioning.InMemoryUserDetailsManager;
 * 
 * @EnableWebSecurity
 * 
 * @Configuration
 * 
 * @Order(1) public class WebMvcConfig extends WebSecurityConfigurerAdapter {
 * 
 * // @Resource(name = "userDetailService") // private UserDetailsService
 * userDetailsService;
 * 
 * // @Bean // public BCryptPasswordEncoder passwordEncoder(){ //
 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // return
 * encoder; // } // @Autowired // public void
 * configureGlobal(AuthenticationManagerBuilder auth) throws Exception { //
 * auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder()
 * ); // }
 * 
 * 
 * @Bean
 * 
 * @Override public UserDetailsService userDetailsServiceBean() throws Exception
 * {
 * 
 * // add users in List List<UserDetails> users = new ArrayList<UserDetails>();
 * 
 * users.add(User.withDefaultPasswordEncoder().username("user")
 * .password("user").roles(new String[] {"USER","ADMIN"}).build());
 * 
 * return new InMemoryUserDetailsManager(users); }
 * 
 * }
 */