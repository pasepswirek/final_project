package pl.sda.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}user").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("{noop}dba").roles("DBA");
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.username, u.password,1 FROM user u WHERE u.username=?")
                .authoritiesByUsernameQuery("SELECT u.username, r.name, 1 " +
                        "FROM user u " +
                        "INNER JOIN user_role ur ON ur.user_id = u.user_id " +
                        "INNER JOIN role r ON r.role_id = ur.role_id " +
                        "WHERE u.username=?")
                .dataSource(dataSource);
//                .passwordEncoder();


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//zabezpieczenie przed wejsciem na poszczególne strony
        http.authorizeRequests()
//                .antMatchers("/","/userHome/").access("hasRole('USER')")
                .antMatchers("/adminHome/**").access("hasRole('ADMIN')")
                .antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/userHome/**").hasRole("USER")
                .antMatchers("/login**", "/register**").permitAll()
                .anyRequest().authenticated();
//                and().csrf().disable();

        http.formLogin()
                .loginPage("/login")//strona z logowaniem
                .loginProcessingUrl("/appLogin")//wymagane ale obsługa w tle
                .usernameParameter("username")//nazwa textbox uzytkownika
                .passwordParameter("pass")//nazwa textbox password
//                .defaultSuccessUrl("/index" , true)//jak ok do do st. głownej
                .defaultSuccessUrl("/" )//jak ok do do st. głownej
                .failureUrl("/error")//jak błąd logowania to do strony z błedem
                .and().rememberMe().tokenValiditySeconds(604800).key("userKey");
        // .failureHandler(authenticationFailureHandler())

        http.csrf().disable()
                .headers().frameOptions().disable();

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
        //  .logoutSuccessHandler(logoutSuccessHandler())
        ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/register**", "/resources/**", "/static/**", "/css/**", "/js/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
