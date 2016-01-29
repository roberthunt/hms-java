package uk.org.nottinghack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.repository.MemberRepository;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class HmsUserDetailsService implements UserDetailsService
{
    private final MemberRepository memberRepository;

    @Autowired
    public HmsUserDetailsService(MemberRepository memberRepository)
    {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return memberRepository.findByUsername(username).orElseThrow(() ->
            new UsernameNotFoundException("Member with username [" + username + "] was not found")
        );
    }
}
