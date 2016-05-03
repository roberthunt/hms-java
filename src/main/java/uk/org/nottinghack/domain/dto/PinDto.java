package uk.org.nottinghack.domain.dto;

import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class PinDto
{
    private int id;
    private String pin;
    private LocalDateTime added;
    private LocalDateTime expiry;

    public PinDto()
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

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public LocalDateTime getAdded()
    {
        return added;
    }

    public void setAdded(LocalDateTime added)
    {
        this.added = added;
    }

    public LocalDateTime getExpiry()
    {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry)
    {
        this.expiry = expiry;
    }
}
