package pl.org.conceptweb.heydeskb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.org.conceptweb.heydeskb.filters.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/building").hasAnyAuthority("ADMIN")
                .antMatchers("/floor").hasAnyAuthority("ADMIN")
                .antMatchers("/floor/company").hasAnyAuthority("ADMIN")
                .antMatchers("/room").hasAnyAuthority("ADMIN")
                .antMatchers("/room/company").hasAnyAuthority("ADMIN")
                
                .antMatchers("/desk").hasAnyAuthority("ADMIN,USER")
                .antMatchers("/desk/company").hasAnyAuthority("ADMIN")
                .antMatchers("/desk/reservation").hasAnyAuthority("ADMIN,USER")
                .antMatchers("/desk/reservation/user").hasAnyAuthority("ADMIN,USER")
                
                .antMatchers("/deskerservation").hasAnyAuthority("ADMIN,USER")
                .antMatchers("/deskerservation/user").hasAnyAuthority("ADMIN,USER")
                .antMatchers("/deskerservation/getallbycompany").hasAnyAuthority("ADMIN,USER")
                .antMatchers("/company").hasAnyAuthority("ADMIN")
//                .antMatchers("/deskerservation/delete").hasAnyAuthority("ADMIN,USER")
//                .antMatchers("/user").hasAuthority("USER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/user").hasAuthority("ADMIN,USER")
                .antMatchers("/user/add").hasAuthority("ADMIN")
                .antMatchers("/user/getbycompany").hasAuthority("ADMIN")
                .antMatchers("/user/delete").hasAuthority("ADMIN")
                .antMatchers("/dropdown").permitAll()
                .antMatchers("/dropdown/building").permitAll()
                .antMatchers("/dropdown/floor").permitAll()
                .antMatchers("/dropdown/room").permitAll()
                .antMatchers("/dropdown/desk").permitAll()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers("/stats").permitAll()
                .antMatchers("/stats/basic").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/").permitAll()

                .anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.headers().frameOptions().disable();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
