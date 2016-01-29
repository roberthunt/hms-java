package uk.org.nottinghack.exception;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class ExistingMemberException extends Exception
{
    public ExistingMemberException(String message)
    {
        super(message);
    }
}
