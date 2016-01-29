package uk.org.nottinghack.repository.impl;

import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import uk.org.nottinghack.domain.MemberStatus;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.QMember;
import uk.org.nottinghack.repository.custom.MemberRepositoryCustom;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MemberRepositoryImpl extends QueryDslRepositorySupport implements MemberRepositoryCustom
{
    public MemberRepositoryImpl()
    {
        super(Member.class);
    }

    @Override
    public Map<MemberStatus, Long> countMembersByStatus()
    {
        // create a map of all possible member statuses and set their count to 0
        Map<MemberStatus, Long> memberStatues = new LinkedHashMap<>(MemberStatus.values().length);
        for (MemberStatus memberStatus : MemberStatus.values())
        {
            memberStatues.put(memberStatus, 0L);
        }

        // find the actual count of members for each status and add them to the map
        QMember member = QMember.member;
        memberStatues.putAll(from(member).groupBy(member.status).map(member.status, member.count()));

        return memberStatues;
    }
}
