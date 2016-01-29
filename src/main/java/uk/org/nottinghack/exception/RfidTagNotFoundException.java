package uk.org.nottinghack.exception;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class RfidTagNotFoundException extends RuntimeException
{
    public RfidTagNotFoundException(String serial)
    {
        super("rfid tag with serial number [" + serial + "] was not found.");
    }
}
