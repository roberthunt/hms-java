package uk.org.nottinghack.service.impl;

import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.tool.ToolBooking;
import uk.org.nottinghack.domain.tool.ToolBookingType;
import uk.org.nottinghack.service.ToolCalendarService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class GoogleCalendarService implements ToolCalendarService
{
    @Override
    public String createToolCalendar(String name)
    {
        return null;
    }

    @Override
    public List<ToolBooking> getBookingsBetweenDates(String calendarId, LocalDateTime from, LocalDateTime to)
    {
        // add events
        List<ToolBooking> events = new ArrayList<>();
        events.add(new ToolBooking(
                "event-0-with-string-id",
                "Dudley Bose",
                ToolBookingType.NORMAL,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 03, 23, 30),
                LocalDateTime.of(2016, 05, 04, 01, 30)
        ));
        events.add(new ToolBooking(
                "event-1-with-string-id",
                "Hoshe Finn",
                ToolBookingType.MAINTAIN,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 04, 05, 00),
                LocalDateTime.of(2016, 05, 06, 13, 00)
        ));
        events.add(new ToolBooking(
                "event-2-with-string-id",
                "Wilson Kime",
                ToolBookingType.INDUCTION,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 06, 14, 30),
                LocalDateTime.of(2016, 05, 06, 15, 30)
        ));
        events.add(new ToolBooking(
                "event-3-with-string-id",
                "Mellanie Rescorai",
                ToolBookingType.MAINTAIN,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 07, 22, 30),
                LocalDateTime.of(2016, 05, 07, 23, 00)
        ));
        events.add(new ToolBooking(
                "event-4-with-string-id",
                "Ozzie Fernandez Isaacs",
                ToolBookingType.NORMAL,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 07, 23, 30),
                LocalDateTime.of(2016, 05, 8, 01, 15)
        ));
        events.add(new ToolBooking(
                "event-5-with-string-id",
                "Nigel Sheldon",
                ToolBookingType.NORMAL,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 9, 00, 00),
                LocalDateTime.of(2016, 05, 10, 00, 00)
        ));
        events.add(new ToolBooking(
                "event-6-with-string-id",
                "Giselle Swinsol",
                ToolBookingType.INDUCTION,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 10, 02, 00),
                LocalDateTime.of(2016, 05, 10, 02, 45)
        ));
        events.add(new ToolBooking(
                "event-7-with-string-id",
                "Vic Russell",
                ToolBookingType.NORMAL,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 10, 05, 00),
                LocalDateTime.of(2016, 05, 10, 05, 15)
        ));
        events.add(new ToolBooking(
                "event-8-with-string-id",
                "Tunde Sutton",
                ToolBookingType.MAINTAIN,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 10, 22, 00),
                LocalDateTime.of(2016, 05, 20, 01, 15)
        ));

        return events;
    }

    @Override
    public Optional<ToolBooking> getNextBooking(String calendarId)
    {
        if (calendarId.equals("r46cbjgesib4eotk0qcaomg2gs@group.calendar.google.com"))
        {
            return Optional.of(new ToolBooking(
                    "event-0-with-string-id",
                    "Giselle Swinsol",
                    ToolBookingType.NORMAL,
                    LocalDateTime.now(),
                    177,
                    LocalDateTime.of(2016, 05, 12, 23, 30),
                    LocalDateTime.of(2016, 05, 13, 01, 30)
            ));
        }


        return Optional.empty();
    }

    @Override
    public List<ToolBooking> getFutureBookings(String calendarId)
    {
        return null;
    }

    @Override
    public List<ToolBooking> getFutureBookingsForMember(String calendarId, int memberId)
    {
        List<ToolBooking> events = new ArrayList<>();
        events.add(new ToolBooking(
                "event-0-with-string-id",
                "Giselle Swinsol",
                ToolBookingType.NORMAL,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 12, 23, 30),
                LocalDateTime.of(2016, 05, 13, 01, 30)
        ));
        events.add(new ToolBooking(
                "event-1-with-string-id",
                "Giselle Swinsol",
                ToolBookingType.MAINTAIN,
                LocalDateTime.now(),
                177,
                LocalDateTime.of(2016, 05, 15, 05, 00),
                LocalDateTime.of(2016, 05, 15, 13, 00)
        ));

        return events;
    }

    @Override
    public void saveBooking(String calendarId, ToolBooking toolBooking)
    {

    }

    @Override
    public void deleteBooking(String calendarId, String toolBookingId)
    {

    }
}
