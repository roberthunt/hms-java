package uk.org.nottinghack.repository;

import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.Permission;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface PermissionRepository extends CrudRepository<Permission, String>
{
    List<Permission> findAll();
}
