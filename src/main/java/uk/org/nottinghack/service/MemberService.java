package uk.org.nottinghack.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.org.nottinghack.domain.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * A CRUD service for Members
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MemberService
{
    Member getMember(Integer memberId);

    Optional<Member> getMemberByEmail(String email);

    Page<Member> searchMembers(Pageable pageable, String searchQuery);

    Page<Member> getMembersByStatus(MemberStatus status, Pageable pageable);

    MemberStatusUpdate getLatestStatusUpdateForMember(Integer memberId);

    MemberEmail getLatestEmailForMember(Integer memberId);

    List<MemberEmail> getEmailsForMember(Integer memberId);

    Map<LocalDate, List<MemberEmail>> getEmailsForMemberGroupedByDate(Integer memberId);

    Map<MemberStatus, Long> getMemberCountsByStatus();

    void updateMemberStatus(@NotNull Integer memberId, @NotNull MemberStatus status);

    void updateMember(Member member);
}
