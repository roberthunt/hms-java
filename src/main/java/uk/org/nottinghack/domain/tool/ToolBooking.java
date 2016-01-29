package uk.org.nottinghack.domain.tool;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class represents the information for a tool booking.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class ToolBooking
{
    private final String id;
    private final String title;
    private final ToolBookingType type;
    private final LocalDateTime booked;
    private final int memberId;
    private final LocalDateTime start;
    private final LocalDateTime end;

    public ToolBooking(String id, String title, ToolBookingType type, LocalDateTime booked, int memberId,
                       LocalDateTime start, LocalDateTime end)
    {
        this.id = id;
        this.title = title;
        this.type = type;
        this.booked = booked;
        this.memberId = memberId;
        this.start = start;
        this.end = end;
    }

    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public ToolBookingType getType()
    {
        return type;
    }

    public LocalDateTime getBooked()
    {
        return booked;
    }

    public int getMemberId()
    {
        return memberId;
    }

    public LocalDateTime getStart()
    {
        return start;
    }

    public LocalDateTime getEnd()
    {
        return end;
    }

    // booking duration in minutes
    public long getDurationInMinutes()
    {
        return Duration.between(getStart(), getEnd()).toMinutes();
    }
}
