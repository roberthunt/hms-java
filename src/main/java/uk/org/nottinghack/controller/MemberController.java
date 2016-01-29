package uk.org.nottinghack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.MemberEmail;
import uk.org.nottinghack.domain.MemberStatusUpdate;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.service.MemberService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping("/members/{memberId}")
public class MemberController
{
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);

    private static final String TEMPLATE_DIR = "member/";
    private static final String MEMBER_VIEW = "view";
    private static final String MEMBER_EDIT = "edit";
    private static final String MEMBER_EMAILS_LIST = "email/list";

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService)
    {
        this.memberService = memberService;
    }

    @RequestMapping
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.VIEW_OTHER_MEMBERS + "')")
    public String view(@PathVariable int memberId, Model model)
    {
        Member member = memberService.getMember(memberId);
        MemberStatusUpdate latestStatusUpdate = memberService.getLatestStatusUpdateForMember(memberId);
        MemberEmail latestEmail = memberService.getLatestEmailForMember(memberId);

        // assemble the view model
        model.addAttribute("member", member);
        model.addAttribute("latestStatusUpdate", latestStatusUpdate);
        model.addAttribute("latestEmail", latestEmail);

        return TEMPLATE_DIR + MEMBER_VIEW;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.EDIT_OTHER_MEMBERS + "')")
    public String editForm(@PathVariable int memberId, Model model)
    {
        Member member = memberService.getMember(memberId);

        // assemble the view model
        model.addAttribute("member", member);

        return TEMPLATE_DIR + MEMBER_EDIT;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@PathVariable int memberId, @Valid Member member, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return TEMPLATE_DIR + MEMBER_EDIT;
        }

        System.out.println(member);

        return "";
    }

    @RequestMapping("/emails")
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.VIEW_EMAILS + "')")
    public String viewEmails(@PathVariable int memberId, Model model)
    {
        Member member = memberService.getMember(memberId);
        Map<LocalDate, List<MemberEmail>> memberEmails = memberService.getEmailsForMemberGroupedByDate(memberId);
        model.addAttribute("member", member);
        model.addAttribute("memberEmails", memberEmails);
        return TEMPLATE_DIR + MEMBER_EMAILS_LIST;
    }

    @RequestMapping(value = "/editPassword", method = RequestMethod.GET)
    @PreAuthorize("#memberId == principal.id")
    public String editPasswordForm(@PathVariable int memberId, Model model)
    {
        return null;
    }
}
