package uk.org.nottinghack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.repository.PermissionRepository;
import uk.org.nottinghack.service.PermissionService;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class PermissionServiceImpl implements PermissionService
{
    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository)
    {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getPermissions()
    {
        return permissionRepository.findAll();
    }
}
