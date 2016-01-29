package uk.org.nottinghack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.domain.RfidTag;
import uk.org.nottinghack.service.MemberService;
import uk.org.nottinghack.service.RfidTagService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping("/rfidtags")
public class RfidTagController
{
    private static final String TEMPLATE_DIR = "rfidtags/";
    private static final String LIST_RFID_TAGS = "list";
    private static final String EDIT_RFID_TAG = "edit";

    private final MemberService memberService;
    private final RfidTagService rfidTagService;

    @Autowired
    public RfidTagController(MemberService memberService, RfidTagService rfidTagService)
    {
        this.memberService = memberService;
        this.rfidTagService = rfidTagService;
    }

    @RequestMapping("/member/{memberId}")
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.VIEW_OTHER_RFID_CARDS + "')")
    public String list(@PathVariable int memberId, Model model)
    {
        Member member = memberService.getMember(memberId);
        List<RfidTag> memberCards = rfidTagService.getRfidTagsForMember(memberId);
        model.addAttribute("member", member);
        model.addAttribute("memberCards", memberCards);

        return TEMPLATE_DIR + LIST_RFID_TAGS;
    }

    @RequestMapping(value = "/edit/{serial}", method = RequestMethod.GET)
    public String editForm(@PathVariable String serial, Model model)
    {
        model.addAttribute("rfidTag", rfidTagService.getRfidTag(serial));

        return TEMPLATE_DIR + EDIT_RFID_TAG;
    }

    // TODO: ERROR - last use is loosing precision between GET -> POST, almost certainly due to the String conversion with the hidden field
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@Valid RfidTag rfidTag, BindingResult bindingResult, RedirectAttributes redirectAttributes)
    {
        RfidTag existingRfidTag = rfidTagService.getRfidTag(rfidTag.getSerial());

        if (bindingResult.hasErrors())
        {
            return TEMPLATE_DIR + EDIT_RFID_TAG;
        }

        // TODO: need to ensure the current user has permission to edit the provided rfidTag

        // TODO: persist changes - only the friendlyName and state fields can be updated, discard any changes to the others

        //TODO: flash attributes may only be available on redirect:/
        redirectAttributes.addFlashAttribute("message", "RFID tag successfully updated");
        redirectAttributes.addAttribute("memberId", 177); // TODO: Hard coded ID - NOPE
        return "redirect:/rfidtags/member/{memberId}";
    }
}
