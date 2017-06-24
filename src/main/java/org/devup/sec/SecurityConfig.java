package org.devup.sec;

/* Module de configuration securité spring*/

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private DataSource dataSource;
	//configuration avec objet AuthenticationManagerBuilder
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/* Pour créer des authentification en mémoire
		auth.inMemoryAuthentication().withUser("admin").password("123").roles("ADMIN","USER");
		auth.inMemoryAuthentication().withUser("user").password("123").roles("USER");
		*/
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select login as principal,pass as credentials,active from users where login=?" )
			.authoritiesByUsernameQuery("select users_login as principal, roles_role as role from users_roles where users_login=?")
			.passwordEncoder(new Md5PasswordEncoder()) //Md5 pour crypter les psswd
			.rolePrefix("ROLE_");
	}
	//configuration avec objet http
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login"); //indiquer le formulaire de connexion login.html
		//http.csrf().disable(); // à désactiver  si l'on veut travailler avec d'autre mode csrf
		http.authorizeRequests().antMatchers("/user/*").hasRole("USER");
		http.authorizeRequests().antMatchers("/admin/*").hasRole("ADMIN");
		http.exceptionHandling().accessDeniedPage("/403"); 
	}

}
