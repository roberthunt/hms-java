package uk.org.nottinghack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.domain.tool.Tool;
import uk.org.nottinghack.domain.tool.ToolBooking;
import uk.org.nottinghack.service.ToolCalendarService;
import uk.org.nottinghack.service.ToolService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping(value = "/tools")
@PreAuthorize("hasAuthority('" + Permission.VIEW_TOOLS + "')")
public class ToolController
{
    private static final String TEMPLATE_DIR = "tool/";
    private static final String LIST_TOOLS = "list";
    private static final String VIEW_TOOL = "view";
    private static final String ADD_TOOL = "add";
    private static final String EDIT_TOOL = "edit";

    // number of days to display on the calendar
    private static final int CALENDAR_DAYS = 7;

    private final ToolService toolService;
    private final ToolCalendarService calendarService;

    @Autowired
    public ToolController(ToolService toolService, ToolCalendarService calendarService)
    {
        this.toolService = toolService;
        this.calendarService = calendarService;
    }

    @RequestMapping
    public String listTools(Model model)
    {
        List<Tool> tools = toolService.getTools();

        // get the next booking for each tool
        for (Tool tool : tools)
        {
            Optional<ToolBooking> nextBooking = calendarService.getNextBooking(tool.getCalendarId());
            nextBooking.ifPresent(booking -> tool.setNextBooking(booking));
        }

        model.addAttribute("tools", tools);

        return TEMPLATE_DIR + LIST_TOOLS;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('" + Permission.ADD_TOOL + "')")
    public String addTool()
    {
        // TODO
        return TEMPLATE_DIR + ADD_TOOL;
    }

    @RequestMapping("/{toolId}/edit")
    @PreAuthorize("hasAuthority('" + Permission.EDIT_TOOL + "')")
    public String editTool(@PathVariable int toolId)
    {
        // TODO
        return TEMPLATE_DIR + EDIT_TOOL;
    }

    @RequestMapping("/{toolId}")
    // TODO: Permissions - member needs to be inducted
    public String viewTool(@PathVariable int toolId,
                           @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<LocalDate> start,
                           Model model)
    {
        // by default, the calendar will start on a Monday of the current week unless a start date is specified
        LocalDateTime thisPeriodStart = start.orElse(LocalDate.now().with(DayOfWeek.MONDAY)).atStartOfDay();
        LocalDateTime lastPeriodStart = thisPeriodStart.minusDays(CALENDAR_DAYS);
        LocalDateTime nextPeriodStart = thisPeriodStart.plusDays(CALENDAR_DAYS);

        // build array of dates representing the days in this period
        LocalDate[] thisPeriodDays = new LocalDate[CALENDAR_DAYS];
        for (int i = 0; i < thisPeriodDays.length ; i++)
        {
            thisPeriodDays[i] = thisPeriodStart.toLocalDate().plusDays(i);
        }

        // build array of times representing 48 time slots from 00:00 at 30 minute intervals
        LocalTime[] timeSlots = new LocalTime[48];
        for (int i = 0; i < timeSlots.length ; i++)
        {
            timeSlots[i] = LocalTime.MIDNIGHT.plusMinutes(i * 30);
        }

        // get the tool and bookings
        Tool tool = toolService.getTool(toolId);
        List<ToolBooking> bookings = calendarService.getBookingsBetweenDates(tool.getCalendarId(), thisPeriodStart, nextPeriodStart);

        model.addAttribute("lastPeriodStart", lastPeriodStart);
        model.addAttribute("nextPeriodStart", nextPeriodStart);
        model.addAttribute("thisPeriodDays", thisPeriodDays);
        model.addAttribute("timeSlots", timeSlots);
        model.addAttribute("now", LocalDateTime.now()); // current date and time (for determining the marker position)
        model.addAttribute("tool", tool);
        model.addAttribute("bookings", bookings);

        return TEMPLATE_DIR + VIEW_TOOL;
    }

}
