package uk.org.nottinghack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.tool.Tool;
import uk.org.nottinghack.domain.tool.ToolBooking;
import uk.org.nottinghack.service.ToolCalendarService;
import uk.org.nottinghack.service.ToolService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping(value = "/tools/{toolId}/booking")
public class ToolBookingController
{
    private static final String TEMPLATE_DIR = "tool/booking/";
    private static final String LIST_BOOKINGS = "list";
    private static final String ADD_BOOKINGS = "add";

    private final ToolService toolService;
    private final ToolCalendarService calendarService;

    @Autowired
    public ToolBookingController(ToolService toolService, ToolCalendarService calendarService)
    {
        this.toolService = toolService;
        this.calendarService = calendarService;
    }

    @RequestMapping
    // TODO: Permissions
    public String listBookings(@PathVariable int toolId, @AuthenticationPrincipal Member currentMember, Model model)
    {
        Tool tool = toolService.getTool(toolId);
        List<ToolBooking> bookings = calendarService.getFutureBookingsForMember(tool.getCalendarId(), currentMember.getId());

        model.addAttribute("tool", tool);
        model.addAttribute("bookings", bookings);

        return TEMPLATE_DIR + LIST_BOOKINGS;
    }

    @RequestMapping("/add")
    @ResponseBody
    // TODO: Permissions
    public String addBooking(@PathVariable int toolId, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ") LocalDateTime t)
    {
        //https://lspace.nottinghack.org.uk/hms/tools/addbooking/1?t=2015-12-23T17:00:00+00:00
        return "NEW REQUEST TO CREATE BOOKING FOR TOOL " + toolId + " IN TIMESLOT " + t;
    }

    @RequestMapping("/{bookingId}/delete")
    // TODO: Permissions
    public String deleteBooking(@PathVariable int toolId, @PathVariable String bookingId)
    {
        return null;
    }
}
