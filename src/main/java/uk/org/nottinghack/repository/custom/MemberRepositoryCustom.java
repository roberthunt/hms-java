package uk.org.nottinghack.repository.custom;

import uk.org.nottinghack.domain.MemberStatus;

import java.util.Map;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MemberRepositoryCustom
{
    Map<MemberStatus, Long> countMembersByStatus();
}
