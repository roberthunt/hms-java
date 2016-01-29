package uk.org.nottinghack.domain.converter;

import uk.org.nottinghack.domain.RfidTagState;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Converter
public final class RfidTagStateAttributeConverter implements AttributeConverter<RfidTagState, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(RfidTagState attribute)
    {
        return attribute.getId();
    }

    @Override
    public RfidTagState convertToEntityAttribute(Integer dbData)
    {
        for (RfidTagState state : RfidTagState.values())
        {
            if (dbData.intValue() == state.getId())
            {
                return state;
            }
        }
        return null;
    }
}
