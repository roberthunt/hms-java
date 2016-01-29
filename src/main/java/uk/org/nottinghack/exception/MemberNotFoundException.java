package uk.org.nottinghack.exception;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MemberNotFoundException extends RuntimeException
{
    public MemberNotFoundException(Integer memberId)
    {
        super("member with id [" + memberId + "] was not found.");
    }
}
