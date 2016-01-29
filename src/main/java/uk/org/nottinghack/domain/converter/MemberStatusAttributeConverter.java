package uk.org.nottinghack.domain.converter;

import uk.org.nottinghack.domain.MemberStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Converter
public final class MemberStatusAttributeConverter implements AttributeConverter<MemberStatus, Integer>
{
    @Override
    public Integer convertToDatabaseColumn(MemberStatus attribute)
    {
        return memberStatusToInteger(attribute);
    }

    @Override
    public MemberStatus convertToEntityAttribute(Integer dbData)
    {
        return integerToMemberStatus(dbData);
    }

    public static Integer memberStatusToInteger(MemberStatus memberStatus)
    {
        return memberStatus.getId();
    }

    public static MemberStatus integerToMemberStatus(Integer integer)
    {
        for (MemberStatus status : MemberStatus.values())
        {
            if (integer.intValue() == status.getId())
            {
                return status;
            }
        }
        return null;
    }
}
