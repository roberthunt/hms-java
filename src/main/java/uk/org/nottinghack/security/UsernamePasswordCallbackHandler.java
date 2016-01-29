package uk.org.nottinghack.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class UsernamePasswordCallbackHandler implements CallbackHandler
{
    private static final Logger log = LoggerFactory.getLogger(UsernamePasswordCallbackHandler.class);

    private final String username;
    private final String password;

    public UsernamePasswordCallbackHandler(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    /**
     * Invoke an array of Callbacks.
     *
     * @param callbacks an array of <code>Callback</code> objects which contain the information requested by an
     * underlying security service to be retrieved or displayed.
     * @throws UnsupportedCallbackException if the implementation of this method does not support one or more of the
     * Callbacks specified in the <code>callbacks</code> parameter.
     */
    public void handle(Callback[] callbacks) throws UnsupportedCallbackException
    {
        for (Callback callback : callbacks)
        {
            if (callback instanceof TextOutputCallback)
            {
                // log the message according to the specified type
                TextOutputCallback toc = (TextOutputCallback) callback;
                switch (toc.getMessageType())
                {
                    case TextOutputCallback.INFORMATION:
                        log.info(toc.getMessage());
                        break;

                    case TextOutputCallback.ERROR:
                        log.error(toc.getMessage());
                        break;

                    case TextOutputCallback.WARNING:
                        log.warn(toc.getMessage());
                        break;

                    default:
                        log.error("Unsupported message type: {}", toc.getMessageType());
                }
            }
            else if (callback instanceof NameCallback)
            {
                NameCallback nc = (NameCallback) callback;
                nc.setName(username);
            }
            else if (callback instanceof PasswordCallback)
            {
                PasswordCallback pc = (PasswordCallback) callback;
                pc.setPassword(password.toCharArray());
            }
            else
            {
                throw new UnsupportedCallbackException(callback, "Unrecognized Callback");
            }
        }
    }
}