package gr.hua.dit.ErasmusRequest.config;

import gr.hua.dit.ErasmusRequest.config.jwt.AuthEntryPointJwt;
import gr.hua.dit.ErasmusRequest.config.jwt.AuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:3000/login")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] staticResources  =  {
                "/static/**",
                "/resources/**",
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/js/**",
                "/vendor.jquery/**"
        };

        http.httpBasic()
                .and()
                .formLogin().loginProcessingUrl("/login").successHandler(loginSuccessHandler).permitAll()
                .and().logout().permitAll()
                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .and().authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/request/form").hasRole("STUDENT")
                .antMatchers("/request/owned").hasRole("STUDENT")
                .antMatchers("/requests/sendTo").hasRole("TEACHER")
                .antMatchers("/requests/approved").hasRole("TEACHER")
                .antMatchers("/requests").denyAll()
                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);;


        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.headers().frameOptions().disable();

        //http.authorizeRequests().antMatchers("/css/**", "/js/**", "/images/**").permitAll().and().formLogin().loginPage("/login").permitAll();


    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("*/static/**");
        web.ignoring().antMatchers("*/images/**");
    }
}
