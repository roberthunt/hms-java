package uk.org.nottinghack.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.org.nottinghack.builder.MemberBuilder;
import uk.org.nottinghack.config.MvcConfig;
import uk.org.nottinghack.config.Profiles;
import uk.org.nottinghack.config.WebSecurityConfig;
import uk.org.nottinghack.context.TestContext;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.service.MemberService;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@ActiveProfiles(Profiles.TEST)
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {TestContext.class, Application.class})
@ContextConfiguration(classes = {TestContext.class, MvcConfig.class, WebSecurityConfig.class})
@WebAppConfiguration
@WithMockUser(username="admin",roles={"USER","ADMIN"})
public class MemberListControllerTest
{
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MemberService memberServiceMock;

    @Before
    public void setUp()
    {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(memberServiceMock);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testViewMember() throws Exception
    {
        Member found = new MemberBuilder()
                .firstAddressLine("First Address Line")
                .id(1)
                .build();

        when(memberServiceMock.getMember(1)).thenReturn(found);

        mockMvc.perform(get("/members/view/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("view"))
                .andExpect(forwardedUrl("view"))
                .andExpect(model().attribute("member", hasProperty("id", is(1))))
                .andExpect(model().attribute("member", hasProperty("firstAddressLine", is(1))));

        verify(memberServiceMock, times(1)).getMember(1);
        verifyNoMoreInteractions(memberServiceMock);
    }
}
