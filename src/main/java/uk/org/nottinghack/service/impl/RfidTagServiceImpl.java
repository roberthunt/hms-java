package uk.org.nottinghack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.domain.RfidTag;
import uk.org.nottinghack.exception.RfidTagNotFoundException;
import uk.org.nottinghack.repository.RfidTagRepository;
import uk.org.nottinghack.service.RfidTagService;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class RfidTagServiceImpl implements RfidTagService
{
    @Autowired
    private RfidTagRepository rfidTagRepository;

    @Override
    @PostAuthorize("returnObject.member.id == principal.id or hasAuthority('" + Permission.VIEW_OTHER_RFID_CARDS + "')")
    public RfidTag getRfidTag(String serial)
    {
        return rfidTagRepository.findBySerial(serial).orElseThrow(() -> new RfidTagNotFoundException(serial));
    }

    @Override
    @PreAuthorize("(rfidTag.member.id == principal.id and hasAuthority('" + Permission.EDIT_OWN_RFID_CARDS + "')) or " +
            "hasAuthority('" + Permission.EDIT_OTHER_RFID_CARDS + "')")
    public RfidTag updateRfidTag(RfidTag rfidTag)
    {
        return rfidTagRepository.save(rfidTag);
    }

    @Override
    public List<RfidTag> getRfidTagsForMember(Integer memberId)
    {
        return rfidTagRepository.findByMemberId(memberId);
    }
}
