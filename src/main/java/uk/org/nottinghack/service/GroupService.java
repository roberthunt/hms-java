package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.Group;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface GroupService
{
    List<Group> getGroups();
}
