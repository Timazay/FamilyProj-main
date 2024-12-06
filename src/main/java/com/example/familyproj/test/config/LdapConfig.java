package com.example.familyproj.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
/*@EnableLdapRepositories(basePackages = "com.example.familyproj")*/
public class LdapConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /*http.authorizeHttpRequests((authorize) -> authorize.anyRequest().fullyAuthenticated())
                .formLogin(Customizer.withDefaults());

        return http.build();*/
        http.authorizeHttpRequests((authorize) -> authorize
                .anyRequest().permitAll()); // Разрешаем доступ ко всем запросам

        return http.build();
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }

    @Bean
    public LdapContextSource contextSource() {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl("ldap://localhost:389");
        ldapContextSource.setUserDn("cn=admin, dc=ramhlocal,dc=com");
        ldapContextSource.setPassword("admin_pass");
        return ldapContextSource;
    }

    @Bean
    AuthenticationManager authManager(BaseLdapPathContextSource source) {
        LdapBindAuthenticationManagerFactory factory = new LdapBindAuthenticationManagerFactory(source);
        factory.setUserDnPatterns("cn={0},cn=user-ro,dc=ramhlocal,dc=com");
        return factory.createAuthenticationManager();
    }

}
