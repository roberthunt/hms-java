package uk.org.nottinghack.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.dto.MemberDto;
import uk.org.nottinghack.mapper.util.FirstElement;
import uk.org.nottinghack.mapper.util.IterableNonInterableUtil;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Mapper(uses = {MemberStatusMapper.class, GroupMapper.class, IterableNonInterableUtil.class, PinMapper.class,
        RfidTagMapper.class}, componentModel = "spring")
public interface MemberMapper
{
    @Mappings({
        @Mapping(source = "account.paymentReference", target = "paymentReference"),
        @Mapping(source = "pins", target = "pin", qualifiedBy = FirstElement.class)
    })
    MemberDto MemberToMemberDto(Member member);
}
