package uk.org.nottinghack.domain.tool;

import uk.org.nottinghack.domain.Described;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum ToolStatus implements Described
{
    IN_USE("In Use"),
    FREE("Available"),
    DISABLED("Disabled");

    private final String description;

    ToolStatus(String description)
    {
        this.description = description;
    }

    @Override
    public String getDescription()
    {
        return description;
    }
}
