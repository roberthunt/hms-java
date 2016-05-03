package uk.org.nottinghack.mapper;

import org.mapstruct.Mapper;
import uk.org.nottinghack.domain.Group;
import uk.org.nottinghack.domain.dto.GroupDto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Mapper(componentModel = "spring")
public interface GroupMapper
{
    GroupDto GroupToGroupDto(Group group);
}
