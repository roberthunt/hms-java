package uk.org.nottinghack.domain.dto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class RfidTagDto
{
    private String serial;

    public RfidTagDto()
    {
        // default no-arg constructor
    }

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }
}
