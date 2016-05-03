package uk.org.nottinghack.mapper;

import org.mapstruct.Mapper;
import uk.org.nottinghack.domain.Pin;
import uk.org.nottinghack.domain.dto.PinDto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Mapper(componentModel = "spring")
public interface PinMapper
{
    PinDto pinToPinDto(Pin pin);
}
