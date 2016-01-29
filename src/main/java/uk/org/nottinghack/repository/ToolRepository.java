package uk.org.nottinghack.repository;

import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.tool.Tool;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface ToolRepository extends CrudRepository<Tool, Integer>
{
    List<Tool> findAll();
}
