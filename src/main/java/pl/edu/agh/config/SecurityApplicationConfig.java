package pl.edu.agh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SpringSocialConfigurer;
import pl.edu.agh.services.implementations.SimpleSocialUsersDetailsService;
import pl.edu.agh.services.interfaces.ISocialUsersDetailsManagementService;

import javax.sql.DataSource;

/**
 * Created by Krzysztof Kicinger on 2014-12-03.
 */
@Configuration
@EnableWebSecurity
public class SecurityApplicationConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ISocialUsersDetailsManagementService usersAuthorizationManagementService;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .withDefaultSchema();*/
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
/*        http.authorizeRequests().antMatchers("/admin*//**")
                .access("hasRole('COMMENTER')").and().formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/login?logout")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403")
                .and().rememberMe()
                .and().apply(new SpringSocialConfigurer().postLoginUrl("/").alwaysUsePostLoginUrl(true));;

        http.csrf().disable().authorizeRequests();*/
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?param.error=bad_credentials")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .and()
                .authorizeRequests()
                .antMatchers("/favicon.ico", "/static-resources/**", "/css/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .rememberMe()
                .and()
                .apply(new SpringSocialConfigurer()
                        .postLoginUrl("/")
                        .alwaysUsePostLoginUrl(true));
        http.csrf().disable().authorizeRequests();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ISocialUsersDetailsManagementService socialUsersDetailService() {
        return new SimpleSocialUsersDetailsService(userDetailsService());
    }

}
