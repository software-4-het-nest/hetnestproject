package be.hetnest.hetnestproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true,proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/",true)
                .failureUrl("/login?login_error=1")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/login*", "/aanvragen*", "/hetnestapi*", "/externeAanbieding*").permitAll()
                .antMatchers("/","/aanbieding*","/aanbiedingen*","/header*","/footer*","/brouwsel*").hasAnyAuthority("PRODUCTIELID","MAGAZIJNIER")
                .antMatchers("/aanbiedingKlaarzetten*","/extraGrondstoffen*","/updateBrouwsel*").hasAuthority("PRODUCTIELID")
                .antMatchers("/**", "/h2/**").hasAuthority("MAGAZIJNIER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403");
        http.csrf().disable();
        http.headers().frameOptions().disable();

    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
