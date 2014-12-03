package pl.edu.agh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.edu.agh.services.interfaces.IUsersAuthorizationManagementService;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Configuration
@EnableWebSecurity
@Import({ApplicationConfig.class})
public class SecurityApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUsersAuthorizationManagementService usersAuthorizationManagementService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersAuthorizationManagementService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/admin/**")
                .access("hasRole('COMMENTER')").and().formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/login?logout")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public IUsersAuthorizationManagementService getUsersAuthorizationManagementService() {
        return usersAuthorizationManagementService;
    }

    public void setUsersAuthorizationManagementService(IUsersAuthorizationManagementService usersAuthorizationManagementService) {
        this.usersAuthorizationManagementService = usersAuthorizationManagementService;
    }
}
