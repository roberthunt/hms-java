package uk.org.nottinghack.util;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockHmsUserSecurityContextFactory.class)
public @interface WithMockHmsUser {

    String username() default "admin";

    int memberId() default 0;

    String[] permissions() default {};
}