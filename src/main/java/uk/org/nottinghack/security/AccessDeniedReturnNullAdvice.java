package uk.org.nottinghack.security;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.springframework.security.access.AccessDeniedException;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Aspect
@DeclarePrecedence("AccessDeniedReturnNullAdvice, *")
public class AccessDeniedReturnNullAdvice
{
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AccessDeniedReturnNullAdvice.class);

    @Around("execution(@uk.org.nottinghack.security.ReturnNullOnAccessDenied * *(..))")
    public Object suppressAccessDeniedException(ProceedingJoinPoint pjp) throws Throwable
    {
        Object returnValue = null;

        try
        {
            returnValue = pjp.proceed();
        }
        catch (AccessDeniedException ade)
        {
            // ignore
            log.info("Access was denied at {}", pjp.toShortString());
        }
        catch (Throwable t)
        {
            throw t;
        }

        return returnValue;
    }


}