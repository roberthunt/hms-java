package uk.org.nottinghack.context;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.org.nottinghack.service.MemberService;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Configuration
public class TestContext
{
    @Bean
    public MemberService memberService()
    {
        return Mockito.mock(MemberService.class);
    }
}
