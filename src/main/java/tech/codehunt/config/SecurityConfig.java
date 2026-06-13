package tech.codehunt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	PasswordEncoder passwordEncoder() {
		PasswordEncoder delegate = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return delegate.encode(rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				if (encodedPassword == null) {
					return false;
				}

				if (!encodedPassword.startsWith("{")) {
					return rawPassword != null && encodedPassword.equals(rawPassword.toString());
				}

				return delegate.matches(rawPassword, encodedPassword);
			}
		};
	}
	
     @Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	 httpSecurity
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/admin/**").authenticated()
				.anyRequest().permitAll()
			)
			.formLogin(form -> form
				.loginPage("/login")
				.defaultSuccessUrl("/admin/dashboard", true)
				.permitAll()
			)
			.logout(logout -> logout
				.logoutUrl("/dologout")
				.logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
			);
		return httpSecurity.build();
	}
}
