package uk.org.nottinghack.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A marker annotation used to indicate that a method should return <code>null</code> instead of throwing an
 * {@link org.springframework.security.access.AccessDeniedException AccessDeniedException}.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ReturnNullOnAccessDenied
{
}
