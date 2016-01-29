package uk.org.nottinghack.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.org.nottinghack.domain.Permission;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping(value = "/members/{memberId}", name = "MSC")
public class MembershipController
{
    @RequestMapping(value = "/acceptDetails}", method = RequestMethod.GET)
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.EDIT_OTHER_MEMBERS + "')")
    public String acceptContactDetails(int memberId, Model model)
    {
        return null;
    }

    @RequestMapping(value = "/rejectDetails}", method = RequestMethod.GET)
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.EDIT_OTHER_MEMBERS + "')")
    public String rejectContactDetails(int memberId, Model model)
    {
        return null;
    }
}
