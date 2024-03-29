package br.ufscar.dc.dsw.pacotesturisticos.config;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.pacotesturisticos.security.UsuarioDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Bean
    public UserDetailsService userDetailsService(){
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable().authorizeRequests()
    	// Controladores REST
    	.antMatchers("/clientes", "/agencias", "/pacotes", "/compras", "/imagens").permitAll()
    	.antMatchers("/clientes/{\\d+}", "/agencias/{\\d+}", "/pacotes/{\\d+}", "/compras/{\\d+}").permitAll()
    	.antMatchers("/pacotes/agencias/{\\d+}").permitAll()
    	.antMatchers("/pacotes/clientes/{\\d+}").permitAll()
    	.antMatchers("/pacotes/destinos/{\\w+}").permitAll()
        .antMatchers("/compras/clientes/{\\w+}").permitAll()
        .antMatchers("/imagens/pacotes/{\\w+}").permitAll()
    	// Demais linhas
    	//.anyRequest().authenticated();
    	//.and().formLogin().loginPage("/login").permitAll()
    	//.and().logout().logoutSuccessUrl("/").permitAll();
        .antMatchers("/error", "/login/**", "/js/**", "/imagem/**","/script/**").permitAll()
        .antMatchers("/css/**", "/image/**", "/webjars/**", "/pacote/listar", "/home").permitAll()
        .antMatchers("/agencia/**", "/cliente/**", "/compra/**").hasRole("ADMIN")
        .antMatchers("/compra/**").hasAnyRole("ADMIN", "USER")
        .antMatchers("/pacote/cadastrar").hasRole("AGENCIA")
        
        .anyRequest().authenticated()
        .and()
        .logout().logoutSuccessUrl("/").permitAll()
        .and()
        .formLogin().loginPage("/login").permitAll();
    }
}
