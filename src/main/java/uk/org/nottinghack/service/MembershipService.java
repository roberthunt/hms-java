package uk.org.nottinghack.service;

import org.jetbrains.annotations.NotNull;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.exception.ExistingMemberException;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MembershipService
{
    void acceptContactDetails(@NotNull Integer prospectiveMemberId, @NotNull Integer adminMemberId);

    void rejectContactDetails(Integer memberId);

    void approveMembership(Integer memberId);

    void revokeMembership(Integer memberId);

    void reinstateMembership(Integer memberId);

    Member register(String emailAddress) throws ExistingMemberException;

    // not certain if these should live here
    void sendMembershipCompleteMail();
    void sendProspectiveMemberReminder();
    void sendContactDetailsReminder();
    void sendSoDetailsReminder();
    void sendSoDetailsToMember();
}
