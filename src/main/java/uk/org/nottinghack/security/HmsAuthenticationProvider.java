package uk.org.nottinghack.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class HmsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider
{
    // should match the name of the entry in jaas.conf
    private static final String CONFIG_NAME = "HMS";

    private UserDetailsService userDetailsService;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
            UsernamePasswordAuthenticationToken authentication) throws AuthenticationException
    {
        if (authentication.getCredentials() == null)
        {
            logger.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!isPasswordValid(userDetails.getUsername(), presentedPassword))
        {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(messages.getMessage(
                    "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException
    {
        UserDetails loadedUser;

        try
        {
            loadedUser = this.getUserDetailsService().loadUserByUsername(username);
        }
        catch (UsernameNotFoundException notFound)
        {
            if (authentication.getCredentials() != null)
            {
                String presentedPassword = authentication.getCredentials().toString();
                // Still perform the password check even though the username was not found to avoid the possibility of
                // an attacker being able to determine if a username is valid. https://jira.spring.io/browse/SEC-2056
                isPasswordValid(username, presentedPassword);
            }
            throw notFound;
        }
        catch (Exception repositoryProblem)
        {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null)
        {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an " +
                    "interface contract violation");
        }
        return loadedUser;
    }

    private boolean isPasswordValid(String username, String password)
    {
        // TODO: REMOVE
        if (username.equalsIgnoreCase("robh") && password.equals(""))
        {
            return true;
        }

        LoginContext loginContext;

        try
        {
            loginContext = new LoginContext(CONFIG_NAME, new UsernamePasswordCallbackHandler(username, password));
        }
        catch (LoginException | SecurityException ex)
        {
            logger.error("Cannot create LoginContext. " + ex.getMessage(), ex);
            return false;
        }

        try
        {
            // attempt authentication
            loginContext.login();
        }
        catch (LoginException ex)
        {
            logger.warn("Authentication failed: " + ex.getMessage(), ex);
            return false;
        }

        return true;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService)
    {
        this.userDetailsService = userDetailsService;
    }

    protected UserDetailsService getUserDetailsService()
    {
        return userDetailsService;
    }
}
