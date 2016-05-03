package uk.org.nottinghack.mapper.util;

import org.mapstruct.Mapper;

import java.util.Collection;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Mapper(componentModel = "spring")
public class IterableNonInterableUtil
{
    @FirstElement
    public <T> T first(Collection<T> collection)
    {
        if (collection != null && !collection.isEmpty())
        {
            return collection.iterator().next();
        }
        else
        {
            return null;
        }
    }
}
