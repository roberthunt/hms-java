package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.tool.ToolBooking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * An interface that describes the operations available for a tool booking calendar.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface ToolCalendarService
{
    /**
     * Creates a new took calendar
     *
     * @param name name for the new calendar
     * @return calendar id
     */
    String createToolCalendar(String name);

    List<ToolBooking> getBookingsBetweenDates(String calendarId, LocalDateTime from, LocalDateTime to);

    /**
     * Gets the next tool booking based on the current date and time for the specified calendar.
     *
     * @param calendarId id of the tool calendar.
     * @return the next tool booking.
     */
    Optional<ToolBooking> getNextBooking(String calendarId);

    // TODO: I Don't like this - seems to be used for internal checks in HMS-PHP so maybe we can hide it and let the
    // Google Calendar implementation deal with it if needed
    List<ToolBooking> getFutureBookings(String calendarId);

    List<ToolBooking> getFutureBookingsForMember(String calendarId, int memberId);

    void saveBooking(String calendarId, ToolBooking toolBooking);

    void deleteBooking(String calendarId, String toolBookingId);
}
