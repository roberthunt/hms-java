package uk.org.nottinghack.domain.tool;

import javax.persistence.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "tl_tools")
public class Tool
{
    @Id
    @GeneratedValue
    @Column(name = "tool_id")
    private int id;

    @Column(name = "tool_address", length = 255)
    private String address;

    @Column(name = "tool_name", length = 20)
    private String name;

    @Column(name = "tool_status")
    @Enumerated(EnumType.STRING)
    private ToolStatus status;

    @Column(name = "tool_restrictions")
    @Enumerated(EnumType.STRING)
    private ToolRestrictionType restrictions;

    @Column(name = "tool_status_text", length = 255)
    private String statusText;

    @Column(name = "tool_pph", length = 10)
    private int pencePerHour;

    @Column(name = "tool_booking_length", length = 10)
    private int defaultBookingLength;

    @Column(name = "tool_length_max", length = 10)
    private int maxBookingLength;

    @Column(name = "tool_calendar", length = 255)
    private String calendarId;

    @Transient
    private ToolBooking nextBooking;

    public Tool()
    {
        // default no-arg constructor
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ToolStatus getStatus()
    {
        return status;
    }

    public void setStatus(ToolStatus status)
    {
        this.status = status;
    }

    public ToolRestrictionType getRestrictions()
    {
        return restrictions;
    }

    public void setRestrictions(ToolRestrictionType restrictions)
    {
        this.restrictions = restrictions;
    }

    public String getStatusText()
    {
        return statusText;
    }

    public void setStatusText(String statusText)
    {
        this.statusText = statusText;
    }

    public int getPencePerHour()
    {
        return pencePerHour;
    }

    public void setPencePerHour(int pencePerHour)
    {
        this.pencePerHour = pencePerHour;
    }

    public int getDefaultBookingLength()
    {
        return defaultBookingLength;
    }

    public void setDefaultBookingLength(int defaultBookingLength)
    {
        this.defaultBookingLength = defaultBookingLength;
    }

    public int getMaxBookingLength()
    {
        return maxBookingLength;
    }

    public void setMaxBookingLength(int maxBookingLength)
    {
        this.maxBookingLength = maxBookingLength;
    }

    public ToolBooking getNextBooking()
    {
        return nextBooking;
    }

    public void setNextBooking(ToolBooking nextBooking)
    {
        this.nextBooking = nextBooking;
    }

    public String getCalendarId()
    {
        return calendarId;
    }

    public void setCalendarId(String calendarId)
    {
        this.calendarId = calendarId;
    }

    public boolean isDisabled()
    {
        return ToolStatus.DISABLED.equals(getStatus());
    }

    public double getPoundsPerHour()
    {
        return getPencePerHour() / 100;
    }
}
