package uk.org.nottinghack.context;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uk.org.nottinghack.controller.MemberController;
import uk.org.nottinghack.mapper.MemberMapper;
import uk.org.nottinghack.service.MemberService;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Configuration
@ComponentScan("uk.org.nottinghack.mapper")
public class TestContext
{
    @Autowired
    MemberMapper memberMapper;

    @Bean
    public MemberController memberController()
    {
        return new MemberController(memberService(), memberMapper);
    }

    @Bean
    public MemberService memberService()
    {
        return Mockito.mock(MemberService.class);
    }
}
