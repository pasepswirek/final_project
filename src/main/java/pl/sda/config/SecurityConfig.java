package pl.sda.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import pl.sda.bussiness.impl.UserDetailsServiceImpl;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;

    public SecurityConfig(DataSource dataSource, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.dataSource = dataSource;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()).userDetailsService(userDetailsService());
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user").password("{noop}user").roles("USER");
////        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("dba").password("{noop}dba").roles("DBA");
//        auth.jdbcAuthentication()
//                .usersByUsernameQuery("SELECT u.username, u.password,1 FROM user u WHERE u.username=?")
//                .authoritiesByUsernameQuery("SELECT u.username, r.name, 1 " +
//                        "FROM user u " +
//                        "INNER JOIN user_role ur ON ur.user_id = u.user_id " +
//                        "INNER JOIN role r ON r.role_id = ur.role_id " +
//                        "WHERE u.username=?")
//                .dataSource(dataSource)
//                .rolePrefix("ROLE_");
////                .passwordEncoder(bCryptPasswordEncoder);
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//zabezpieczenie przed wejsciem na poszczególne strony
        http.authorizeRequests()
                .antMatchers("/adminHome/**").hasRole("ADMIN")
                .antMatchers("/dba/**").access("hasRole('ADMIN') or hasRole('DBA')")
                .antMatchers("/userHome/**").hasRole("USER")
                .antMatchers("/","/login**", "/register**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/error");
//                .and().csrf().disable();

        http.formLogin()
                .loginPage("/login")//strona z logowaniem
                .loginProcessingUrl("/appLogin")//wymagane ale obsługa w tle
                .usernameParameter("username")//nazwa textbox uzytkownika
                .passwordParameter("pass")//nazwa textbox password
                .successHandler(authenticationSuccessHandler)
//                .defaultSuccessUrl("/index" , true)//jak ok do do st. głownej
                .failureUrl("/login?error")//jak błąd logowania to do strony z błedem
                .and().rememberMe().tokenValiditySeconds(604800).key("userKey");
        // .failureHandler(authenticationFailureHandler())

        http.csrf().disable()
                .headers().frameOptions().disable();//wyłaczenie opcji zapobiegające atakom
        http.logout()
                .logoutSuccessUrl("/login")
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID");

    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/register**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
//    }



//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.addDialect(new SpringSecurityDialect());
//        return templateEngine;
//    }

}
