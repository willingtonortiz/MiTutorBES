package com.mitutor.config;

import com.mitutor.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // Configurar el Authentication manager para que sepa de donde cargar
        // el usuario

        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }



    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // No necesitamos CSRF para este caso
        //httpSecurity.csrf().disable()
        // No autenticar este request
        // .authorizeRequests().antMatchers("/authenticate").permitAll()

        // Eliminar esta linea y descomentar las 2 para utilizar JWT
        //	.authorizeRequests().anyRequest().permitAll().and()
        // Las demas rutas deben autenticarse
        // .anyRequest().authenticated().and()
        // Asegurarse de utilizar una sesión sin estado [NO SE USAN SESIONES]
        //	.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
        //	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Agregar el filtro para validar tokens con cada request
        // httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/authentication").permitAll()
                .anyRequest().authenticated();
    }
    */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers(HttpMethod.POST, "api/authentication").permitAll().and()
                //.authorizeRequests().anyRequest().authenticated().and()
                .authorizeRequests().anyRequest().permitAll();
    }
}
