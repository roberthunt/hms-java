package uk.org.nottinghack.domain.tool;

import uk.org.nottinghack.domain.Described;

/**
 * Used to indicate the type of restriction on a tool. A restricted tool requires the user to be inducted before they
 * can make bookings or use the tool.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum ToolRestrictionType implements Described
{
    RESTRICTED("Restricted"),
    UNRESTRICTED("Unrestricted");

    private final String description;

    ToolRestrictionType(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
