package uk.org.nottinghack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.Group;
import uk.org.nottinghack.repository.GroupRepository;
import uk.org.nottinghack.service.GroupService;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class GroupServiceImpl implements GroupService
{
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository)
    {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getGroups()
    {
        return groupRepository.findAll();
    }
}
