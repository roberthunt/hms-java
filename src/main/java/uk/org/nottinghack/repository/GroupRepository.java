package uk.org.nottinghack.repository;

import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.Group;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface GroupRepository extends CrudRepository<Group, String>
{
    List<Group> findAll();
}
