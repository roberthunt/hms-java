package uk.org.nottinghack.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import uk.org.nottinghack.domain.Group;
import uk.org.nottinghack.domain.Member;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class WithMockHmsUserSecurityContextFactory implements WithSecurityContextFactory<WithMockHmsUser>
{
    @Override
    public SecurityContext createSecurityContext(WithMockHmsUser customUser)
    {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Member principal = new Member();
        principal.setId(customUser.memberId());
        principal.setUsername(customUser.username());

        Group group = new Group();
        group.setPermissions(new HashSet<>(Arrays.asList(customUser.permissions())));
        principal.setGroups(Collections.singleton(group));

        Authentication auth = new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
