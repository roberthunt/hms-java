package uk.org.nottinghack.mapper;

import org.mapstruct.Mapper;
import uk.org.nottinghack.domain.MemberStatus;
import uk.org.nottinghack.domain.dto.MemberStatusDto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Mapper(componentModel = "spring")
public class MemberStatusMapper
{
    MemberStatusDto memberStatusToMemberStatusDto(MemberStatus memberStatus)
    {
        if ( memberStatus == null ) {
            return null;
        }

        MemberStatusDto memberStatusDto = new MemberStatusDto();

        memberStatusDto.setId( memberStatus.getId() );
        memberStatusDto.setTitle( memberStatus.getTitle() );

        return memberStatusDto;
    }
}
