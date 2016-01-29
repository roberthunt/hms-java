package uk.org.nottinghack.domain.tool;

import uk.org.nottinghack.domain.Described;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum ToolBookingType implements Described
{
    NORMAL("normal"),
    INDUCTION("induction"),
    MAINTAIN("maintenance");

    private final String description;

    ToolBookingType(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
