package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.Permission;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface PermissionService
{
    List<Permission> getPermissions();
}
