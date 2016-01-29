package uk.org.nottinghack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.tool.Tool;
import uk.org.nottinghack.repository.ToolRepository;
import uk.org.nottinghack.service.ToolService;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class ToolServiceImpl implements ToolService
{
    private final ToolRepository toolRepository;

    @Autowired
    public ToolServiceImpl(ToolRepository toolRepository)
    {
        this.toolRepository = toolRepository;
    }

    @Override
    public Tool getTool(int toolId)
    {
        return toolRepository.findOne(toolId);
    }

    @Override
    public List<Tool> getTools()
    {
        return toolRepository.findAll();
    }
}
