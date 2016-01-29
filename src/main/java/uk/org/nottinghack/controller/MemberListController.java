package uk.org.nottinghack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.TemplateEngine;
import uk.org.nottinghack.domain.*;
import uk.org.nottinghack.domain.converter.MemberStatusAttributeConverter;
import uk.org.nottinghack.domain.form.RegistrationForm;
import uk.org.nottinghack.domain.form.Search;
import uk.org.nottinghack.exception.ExistingMemberException;
import uk.org.nottinghack.service.EmailService;
import uk.org.nottinghack.service.MailinglistService;
import uk.org.nottinghack.service.MemberService;
import uk.org.nottinghack.service.MembershipService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping("/members")
public class MemberListController
{
    private static final Logger log = LoggerFactory.getLogger(MemberListController.class);

    private static final String TEMPLATE_DIR = "member/";
    private static final String MEMBER_INDEX = "members";
    private static final String MEMBER_LIST = "list";
    private static final String REGISTER = "register";

    // TODO: Autowire via constructor rather than field injection
    @Autowired
    private MemberService memberService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MailinglistService mailinglistService;

    // TODO: remove, testing only
    @Autowired
    private EmailService emailService;

    @Autowired
    private TemplateEngine templateEngine;

    @RequestMapping
    public String index(Model model)
    {
        Map<MemberStatus, Long> memberCountsByStatus = memberService.getMemberCountsByStatus();
        long memberCount = memberCountsByStatus.values().stream().mapToLong(Long::longValue).sum();
        model.addAttribute("memberCountsByStatus", memberCountsByStatus);
        model.addAttribute("memberCount", memberCount);
        model.addAttribute("search", new Search()); // TODO, could be model attribute?
        return TEMPLATE_DIR + MEMBER_INDEX;
    }

    // TODO: see if this can call listMembersWithStatus to avoid duplication
    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_LIST + "')")
    public String listMembers(@RequestParam(required = false) String query, Pageable pageable, Model model)
    {
        Page<Member> members = memberService.searchMembers(pageable, query);
        model.addAttribute("members", members);
        return TEMPLATE_DIR + MEMBER_LIST;
    }

    @RequestMapping("/status/{id}")
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_LIST + "')")
    public String listMembersWithStatus(@PathVariable("id") Integer id, Pageable pageable, Model model)
    {
        MemberStatus memberStatus = MemberStatusAttributeConverter.integerToMemberStatus(id);
        Page<Member> members = memberService.getMembersByStatus(memberStatus, pageable);
        model.addAttribute("members", members);
        model.addAttribute("memberStatus", memberStatus);
        return TEMPLATE_DIR + MEMBER_LIST;
    }

    @RequestMapping(value="/register")
    public String registrationForm(@ModelAttribute RegistrationForm registrationForm, Model model)
    {
        model.addAttribute("allMailingLists", mailinglistService.getMailingLists());
        return TEMPLATE_DIR + REGISTER;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(RegistrationForm registrationForm, Model model, RedirectAttributes redirectAttributes)
    {
        try
        {
            membershipService.register(registrationForm.getEmail());
        }
        catch (ExistingMemberException e)
        {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/login";
        }
        return TEMPLATE_DIR + REGISTER;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test()
    {
//        Member member = memberService.getMember(177);
//
//        final Context ctx = new Context();
//        ctx.setVariable("name", "tester");
//        ctx.setVariable("member", member);
//
//        final String htmlContent = this.templateEngine.process("email/membership_complete", ctx);
//
//        return htmlContent;
        emailService.send("robert.hunt@mpec.co.uk", "testing", "HELLO<br /> WORLD!");
        return "SENT!";
    }

    @RequestMapping(value="/email/status/{statusId}")
    public String emailMembersWithStatus(@PathVariable int statusId)
    {
        return "email";
    }

    @RequestMapping(value="/uploadCsv")
    public String uploadCsv(Model model)
    {
        return "uploadCsv";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(HttpServletRequest req, Exception exception)
    {
        log.error("Request: " + req.getRequestURL() + " raised " + exception, exception);
        return "redirect:/" + MEMBER_INDEX;
    }
}
