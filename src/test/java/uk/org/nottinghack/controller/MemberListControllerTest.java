package uk.org.nottinghack.controller;

import javax.servlet.Filter;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uk.org.nottinghack.builder.MemberBuilder;
import uk.org.nottinghack.config.MvcConfig;
import uk.org.nottinghack.config.Profiles;
import uk.org.nottinghack.config.WebSecurityConfig;
import uk.org.nottinghack.context.TestContext;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.MemberStatus;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.service.MemberService;
import uk.org.nottinghack.util.WithMockHmsUser;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@ActiveProfiles(Profiles.TEST)
@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = {TestContext.class, Application.class})
@ContextConfiguration(classes = {TestContext.class, WebSecurityConfig.class, MvcConfig.class})
@WebAppConfiguration
public class MemberListControllerTest
{
    private MockMvc mockMvc;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MemberService memberServiceMock;

    private Member MEMBER_WHO_HAS_JOINED;
    private Member MEMBER_WHO_HAS_NOT_JOINED;
    private LocalDate JOIN_DATE;

    @Before
    public void setUp()
    {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(memberServiceMock);

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity(springSecurityFilterChain))
                .build();

        JOIN_DATE = LocalDate.of(2016, Month.JANUARY, 2);

        MEMBER_WHO_HAS_JOINED = new MemberBuilder()
            .id(Integer.MAX_VALUE)
            .status(MemberStatus.CURRENT_MEMBER)
            .balance(10)
            .creditLimit(20)
            .joinDate(JOIN_DATE)
            .unlockText("Hello")
            .contactNumber("0123456789")
            .build();

        MEMBER_WHO_HAS_NOT_JOINED = new MemberBuilder()
                .id(Integer.MAX_VALUE)
                .status(MemberStatus.PROSPECTIVE_MEMBER)
                .balance(10)
                .creditLimit(20)
                .joinDate(JOIN_DATE)
                .unlockText("Hello")
                .contactNumber("0123456789")
                .build();
    }

    // TODO: maybe the expression fails because the principal is not a Member object? It's just a string in the mocked ctx

    @Test
    public void testViewMember() throws Exception
    {
        Member found = new MemberBuilder()
                .firstAddressLine("First Address Line")
                .status(MemberStatus.CURRENT_MEMBER)
                .build();

        when(memberServiceMock.getMember(1)).thenReturn(found);

        Member principal = new Member();
        principal.setUsername("user");

        // MockMvc doesn't support AssertJ so we're stuck with Hamcrest :(
        mockMvc.perform(get("/members/{id}", 1).with(user(principal)))
                .andExpect(status().isOk())
                .andExpect(view().name("member/view"))
                .andExpect(forwardedUrl("member/view"))
                .andExpect(model().attribute("member", hasProperty("id", is(1))))
                .andExpect(model().attribute("member", hasProperty("firstAddressLine", is("First Address Line"))));

        verify(memberServiceMock, times(1)).getMember(1);
        verify(memberServiceMock, times(1)).getLatestEmailForMember(1);
        verify(memberServiceMock, times(1)).getLatestStatusUpdateForMember(1);
        verifyNoMoreInteractions(memberServiceMock);
    }

    private ResultActions viewMember(Member member) throws Exception
    {
        when(memberServiceMock.getMember(anyInt())).thenReturn(member);

        return mockMvc.perform(get("/members/{id}", member.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("member/view"))
                .andExpect(forwardedUrl("member/view"));
    }

    @Test
    @WithMockHmsUser
    public void viewMember_WithNoPermissions_ShouldRedirectToIndex() throws Exception
    {
        when(memberServiceMock.getMember(anyInt())).thenReturn(MEMBER_WHO_HAS_JOINED);

        mockMvc.perform(get("/members/{id}", MEMBER_WHO_HAS_JOINED.getId()))
                .andExpect(status().isForbidden())
                .andExpect(forwardedUrl("/"));
    }

    // member who has joined

    @Test
    @WithMockHmsUser(permissions = Permission.VIEW_OTHER_MEMBERS)
    public void viewMember_WhoHasJoined_ShouldSeeJoinDate() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_JOINED).andExpect(model().attribute("member", hasProperty("joinDate", notNullValue())));
    }

    @Test
    @WithMockHmsUser(permissions = Permission.VIEW_OTHER_MEMBERS)
    public void viewMember_WhoHasJoined_ShouldSeeUnlockText() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_JOINED).andExpect(model().attribute("member", hasProperty("unlockText", notNullValue())));
    }

    @Test
    @WithMockHmsUser(permissions = {Permission.VIEW_OTHER_MEMBERS, Permission.VIEW_MEMBER_ADMIN_FEATURES})
    public void viewMember_WhoHasJoined_AsUserWithPermission_VIEW_MEMBER_ADMIN_FEATURES_ShouldSeeBalance() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_JOINED).andExpect(model().attribute("member", hasProperty("balance", is(10))));
    }

    @Test
    @WithMockHmsUser(permissions = {Permission.VIEW_OTHER_MEMBERS, Permission.VIEW_MEMBER_ADMIN_FEATURES})
    public void viewMember_WhoHasJoined_AsUserWithPermission_VIEW_MEMBER_ADMIN_FEATURES_ShouldSeeCreditLimit() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_JOINED).andExpect(model().attribute("member", hasProperty("creditLimit", is(20))));
    }

    // TODO: need inverse of above tests: eg - viewMember_WhoHasJoined_ShouldNotSeeCreditLimit
    // TODO: they could be more generic though as we don't care about 'WhoHasJoined' as it should not affect viability

    // member who has not joined

    @Test
    @WithMockHmsUser(permissions = {Permission.VIEW_OTHER_MEMBERS})
    public void viewMember_WhoHasNotJoined_ShouldNotSeeJoinDate() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_NOT_JOINED).andExpect(model().attribute("member", hasProperty("joinDate", nullValue())));
    }

    @Test
    @WithMockHmsUser(permissions = {Permission.VIEW_OTHER_MEMBERS})
    public void viewMember_WhoHasNotJoined_ShouldNotSeeUnlockText() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_NOT_JOINED).andExpect(model().attribute("member", hasProperty("unlockText", nullValue())));
    }

    @Test
    @WithMockHmsUser(permissions = {Permission.VIEW_OTHER_MEMBERS, Permission.VIEW_MEMBER_ADMIN_FEATURES})
    public void viewMember_WhoHasNotJoined_AsUserWithPermission_VIEW_MEMBER_ADMIN_FEATURES_ShouldNotSeeBalance() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_NOT_JOINED).andExpect(model().attribute("member", hasProperty("balance", nullValue())));
    }

    @Test
    @WithMockHmsUser(permissions = {Permission.VIEW_OTHER_MEMBERS, Permission.VIEW_MEMBER_ADMIN_FEATURES})
    public void viewMember_WhoHasNotJoined_AsUserWithPermission_VIEW_MEMBER_ADMIN_FEATURES_ShouldNotSeeCreditLimit() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_NOT_JOINED).andExpect(model().attribute("member", hasProperty("creditLimit", nullValue())));
    }

    // 4 cases for viewing own profile

    @Test
    // TODO: come up with a better way to link the principal id to the member being viewed
    @WithMockHmsUser(memberId = Integer.MAX_VALUE)
    public void viewMemberWhoIsCurrentUser_WhoHasJoined_ShouldSeeBalance() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_JOINED).andExpect(model().attribute("member", hasProperty("balance", is(10))));
    }

    @Test
    // TODO: come up with a better way to link the principal id to the member being viewed
    @WithMockHmsUser(memberId = Integer.MAX_VALUE)
    public void viewMemberWhoIsCurrentUser_WhoHasNotJoined_ShouldNotSeeBalance() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_NOT_JOINED).andExpect(model().attribute("member", hasProperty("balance", nullValue())));
    }

    @Test
    // TODO: come up with a better way to link the principal id to the member being viewed
    @WithMockHmsUser(memberId = Integer.MAX_VALUE)
    public void viewMemberWhoIsCurrentUser_ShouldSeeContactNumber() throws Exception
    {
        viewMember(MEMBER_WHO_HAS_NOT_JOINED).andExpect(model().attribute("member", hasProperty("contactNumber", notNullValue())));
    }

    // TODO: other address fields
}