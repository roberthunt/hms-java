package uk.org.nottinghack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uk.org.nottinghack.security.HmsAuthenticationProvider;

import java.util.Collections;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, mode = AdviceMode.ASPECTJ)
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Value("${hms.register.subnet}")
    private String registerSubnet;

    @Value("${hms.kerberos.realm}")
    private String kerberosRealm;

    @Value("${hms.kerberos.kdc}")
    private String kerberosKdc;

    @Autowired
    private UserDetailsService userDetailsService;

    @Profile(Profiles.CONTINUOUS_INTEGRATION)
    @Bean
    public UserDetailsService testUserDetailsService()
    {
        User user = new User("admin", "admin", Collections.<GrantedAuthority>emptyList());
        return new InMemoryUserDetailsManager(Collections.singletonList(user));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/hms/**", "/sniffy/**").permitAll()
                .antMatchers("/members/register").hasIpAddress(registerSubnet)
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
            .and()
                .logout()
                 // allows logout via GET method but disables CSRF protection for the logout url
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
            .and()
                // redirect to homepage when user is not authorized to access a uri
                .exceptionHandling().accessDeniedPage("/");
    }

    @Bean
    protected AuthenticationProvider hmsAuthenticationProvider()
    {
        HmsAuthenticationProvider authenticationProvider = new HmsAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        return authenticationProvider;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        System.setProperty("java.security.auth.login.config", "jaas.conf");
        System.setProperty("java.security.krb5.realm", kerberosRealm);
        System.setProperty("java.security.krb5.kdc", kerberosKdc);

        auth.authenticationProvider(hmsAuthenticationProvider());
    }

}
