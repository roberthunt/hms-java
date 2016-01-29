package uk.org.nottinghack.domain.tool;

import uk.org.nottinghack.domain.Described;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum ToolUserLevel implements Described
{
    LVL_USER("USER"),
    LVL_INDUCTOR("INDUCTOR"),
    LVL_MAINTAINER("MAINTAINER");

    private final String description;

    ToolUserLevel(String description)
    {
        this.description = description;
    }

    public String getDescription()
    {
        return description;
    }
}
