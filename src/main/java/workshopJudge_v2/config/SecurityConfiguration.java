package workshopJudge_v2.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import workshopJudge_v2.model.entity.enumeration.RoleType;
import workshopJudge_v2.repository.UserEntityRepository;
import workshopJudge_v2.service.impl.JudgeApplicationUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .authorizeHttpRequests(
                authorizeRequests -> authorizeRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/", "/users/login", "/users/login-error" ).permitAll()
                        .requestMatchers("/users/register").permitAll()
                        .requestMatchers("/roles/**").hasRole(RoleType.ADMIN.name())
                        .requestMatchers("/exercises/**").hasRole(RoleType.ADMIN.name())
                        .anyRequest().authenticated()
                ).formLogin(formLogin -> formLogin
                        .loginPage("/users/login")
                        .usernameParameter("username")  //UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY
                        .passwordParameter("password") //UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY
                        .defaultSuccessUrl("/home", true)
                        .failureForwardUrl("/users/login-error")
                ).logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessUrl("/").permitAll()
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
        ).build();

    }

    @Bean
    public UserDetailsService userDetailsService(UserEntityRepository userRepository) {

        return new JudgeApplicationUserDetailsService(userRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }
}
