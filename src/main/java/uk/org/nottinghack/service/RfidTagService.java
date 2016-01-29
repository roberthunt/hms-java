package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.RfidTag;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface RfidTagService
{
    RfidTag getRfidTag(String serial);

    RfidTag updateRfidTag(RfidTag rfidTag);

    List<RfidTag> getRfidTagsForMember(Integer memberId);
}
