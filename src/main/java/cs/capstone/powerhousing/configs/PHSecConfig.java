package cs.capstone.powerhousing.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class PHSecConfig{


    private UserDetailsService userDetailsService;


    public PHSecConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return  userDetailsService;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


//    @Bean
//    WebSecurityCustomizer configureWebSecurity() {
//        return (web) -> web.ignoring().requestMatchers("/resources/**");
//    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(encoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

       return httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request-> request
                        .requestMatchers("/","/home","/userLogin","/calculator","/processCalculation",
                                "/createUser","/processRegistration", "/profiles", "/saved", "/saveProfile","/exportToPDF",
                                "/showMap", "/reports/**", "/about", "/css/**", "/js/**").permitAll()
                        .requestMatchers("/api/**","/maps/**", "/report",  "/searchProfile").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/userLogin")
                        .successHandler(new AuthSuccessHandler("/calculator"))
                        .permitAll()

                )
                .httpBasic(Customizer.withDefaults())
                .logout(config -> config.logoutSuccessUrl("/home"))
                .build();
    }

}
