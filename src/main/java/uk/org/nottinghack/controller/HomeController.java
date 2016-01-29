package uk.org.nottinghack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.org.nottinghack.domain.MemberStatus;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping("/")
public class HomeController
{
    private static final String HOME = "home";
    private static final String STATUS = "status";

    @RequestMapping
    public String index()
    {
        return HOME;
    }

    @RequestMapping("/status")
    public String status(Model model)
    {
        model.addAttribute("memberStatuses", MemberStatus.values());
        return STATUS;
    }
}
