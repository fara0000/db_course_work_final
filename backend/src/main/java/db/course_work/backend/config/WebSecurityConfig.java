package db.course_work.backend.config;

import db.course_work.backend.filters.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter authenticationJwtTokenFilter() {
        return new JwtFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
                httpSecurity
                .httpBasic().disable()
                .csrf().disable()
                .authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers(HttpMethod.GET, "/").not().fullyAuthenticated()
                .antMatchers(HttpMethod.POST, "/login", "/register").not().fullyAuthenticated()
                //Доступ разрешен всем пользователей
                .antMatchers("/", "/static/**").permitAll()
                //Доступ только для авторизованных пользователей
                .antMatchers("/main/**").authenticated()
                .antMatchers(HttpMethod.GET, "/main", "/").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
