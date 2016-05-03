package uk.org.nottinghack.mapper;

import org.mapstruct.Mapper;
import uk.org.nottinghack.domain.RfidTag;
import uk.org.nottinghack.domain.dto.RfidTagDto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Mapper(componentModel = "spring")
public interface RfidTagMapper
{
    RfidTagDto RfidTagToRfidTagDto(RfidTag rfidTag);
}
