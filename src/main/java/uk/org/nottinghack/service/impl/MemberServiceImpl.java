package uk.org.nottinghack.service.impl;

import com.mysema.query.BooleanBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.*;
import uk.org.nottinghack.exception.MemberNotFoundException;
import uk.org.nottinghack.repository.*;
import uk.org.nottinghack.security.ReturnNullOnAccessDenied;
import uk.org.nottinghack.service.MemberService;

import java.time.LocalDate;
import java.util.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class MemberServiceImpl implements MemberService
{
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberStatusUpdateRepository memberStatusUpdateRepository;

    @Autowired
    private MemberEmailRepository memberEmailRepository;

    @Override
    public Member getMember(Integer memberId)
    {
        return memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
    }

    @Override
    public Optional<Member> getMemberByEmail(String email)
    {
        return memberRepository.findByEmail(email);
    }

    @Override
    public Page<Member> searchMembers(Pageable pageable, String searchQuery)
    {
        QMember qMember = QMember.member;
        BooleanBuilder builder = new BooleanBuilder();

        // fields to search
        builder.or(qMember.firstname.contains(searchQuery));
        builder.or(qMember.surname.contains(searchQuery));
        builder.or(qMember.account.paymentReference.contains(searchQuery));

        return memberRepository.findAll(builder, pageable);
    }

    @Override
    public Page<Member> getMembersByStatus(MemberStatus status, Pageable pageable)
    {
        return memberRepository.findByStatus(status, pageable);
    }

    @Override
    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_ADMIN_FEATURES + "')")
    public MemberStatusUpdate getLatestStatusUpdateForMember(Integer memberId)
    {
        return memberStatusUpdateRepository.findFirstByMemberIdOrderByTimestampDesc(memberId);
    }

    @Override
    public MemberEmail getLatestEmailForMember(Integer memberId)
    {
        return memberEmailRepository.findFirstByMemberIdOrderByTimestampDesc(memberId);
    }

    @Override
    public List<MemberEmail> getEmailsForMember(Integer memberId)
    {
        return memberEmailRepository.findByMemberIdOrderByTimestampDesc(memberId);
    }

    @Override
    public Map<LocalDate, List<MemberEmail>> getEmailsForMemberGroupedByDate(Integer memberId)
    {
        List<MemberEmail> memberEmails = getEmailsForMember(memberId);
        Map<LocalDate, List<MemberEmail>> groupedMemberEmails = new LinkedHashMap<>(memberEmails.size());

        for (MemberEmail email : memberEmails)
        {
            LocalDate key = email.getTimestamp().toLocalDate();

            // if the date key doesn't exist in the map
            if (!groupedMemberEmails.containsKey(key))
            {
                // add the key and initialize a new list value
                groupedMemberEmails.put(key, new ArrayList<>());
            }

            // add email to the list for the date
            groupedMemberEmails.get(key).add(email);
        }

        return groupedMemberEmails;
    }

    @Override
    public Map<MemberStatus, Long> getMemberCountsByStatus()
    {
        return memberRepository.countMembersByStatus();
    }

    @Override
    public void updateMemberStatus(@NotNull Integer memberId, @NotNull MemberStatus status)
    {

    }

    @Override
    @PreAuthorize("#member.id == principal.id or hasAuthority('" + Permission.EDIT_OTHER_MEMBERS + "')")
    public void updateMember(Member member)
    {
        // TODO: lookup existing member
        // merge editable fields into existing entity
        // check for change before merging

        // TODO: EDIT_OTHER_MEMBERS gives full write access to members
        // TODO: if a member has restricted view permissions on a member should that also restrict editable fields?
        // EG: if a member is not able to see which groups another member is in then they should probably also not
        // be able to see them on the edit form!
    }
}
