package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.tool.Tool;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface ToolService
{
    Tool getTool(int toolId);

    List<Tool> getTools();
}
