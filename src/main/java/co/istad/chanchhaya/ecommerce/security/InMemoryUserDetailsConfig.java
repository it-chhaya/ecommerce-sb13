package co.istad.chanchhaya.ecommerce.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserDetailsConfig {

    @Bean
    public InMemoryUserDetailsManager configureUserDetails() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        // Create a user with ROLE_CUSTOMER
        UserDetails customerUser = User.withUsername("customer")
                .password("{noop}qwerqwer")
                .roles("CUSTOMER")
                .build();

        // Create a user with ROLE_STAFF
        UserDetails staffUser = User.withUsername("staff")
                .password("{noop}qwerqwer")
                .roles("STAFF")
                .build();

        // Create a user with ROLE_ADMIN
        UserDetails adminUser = User.withUsername("admin")
                .password("{noop}qwerqwer")
                .roles("ADMIN")
                .build();

        manager.createUser(customerUser);
        manager.createUser(staffUser);
        manager.createUser(adminUser);

        return manager;
    }

}
