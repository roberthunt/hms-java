package uk.org.nottinghack.service.impl;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.EmailTemplate;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.MemberStatus;
import uk.org.nottinghack.exception.ExistingMemberException;
import uk.org.nottinghack.service.EmailService;
import uk.org.nottinghack.service.MemberService;
import uk.org.nottinghack.service.MembershipService;

import java.util.Optional;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class MembershipServiceImpl implements MembershipService
{
    private final MemberService memberService;
    private final  EmailService emailService;

    @Autowired
    public MembershipServiceImpl(MemberService memberService, EmailService emailService)
    {
        this.memberService = memberService;
        this.emailService = emailService;
    }

    @Override
    public void acceptContactDetails(@NotNull Integer prospectiveMemberId, @NotNull Integer adminMemberId)
    {
        Member member = memberService.getMember(prospectiveMemberId);

        // check member has the status WAITING_FOR_MEMBERSHIP_ADMIN_TO_APPROVE_CONTACT_DETAILS (3)
        // verify the the member has been assigned an account (payment reference)
        // ask MemberService to update the member status to WAITING_FOR_STANDING_ORDER_PAYMENT (4)
        // email member -> contact details accepted, here are the standing order details
        // email membership team -> impending payment

        // emailService.sendEmail(TEMPLATE.STANDING_ORDER_DETAILS, prospectiveMemberId);
        // emailService.sendEmail(TEMPLATE.IMPENDING_PAYMENT, prospectiveMemberId, membership@nottinghack.org.uk);

        // sendEmail(template, member) // sends email to member
        // sendEmail(template, member, to) // sends email to address
    }

    @Override
    public void rejectContactDetails(Integer memberId)
    {

    }

    @Override
    public void approveMembership(Integer memberId)
    {

    }

    @Override
    public void revokeMembership(Integer memberId)
    {

    }

    @Override
    public void reinstateMembership(Integer memberId)
    {

    }

    @Override
    public Member register(String emailAddress) throws ExistingMemberException
    {
        if (memberService.getMemberByEmail(emailAddress).isPresent())
        {
            new ExistingMemberException("User with that e-mail already exists.");
        }
        else
        {
            Member member = new Member();
            member.setEmail(emailAddress);
            member.setStatus(MemberStatus.PROSPECTIVE_MEMBER);

            emailService.send(EmailTemplate.NOTIFY_ADMINS_MEMBER_ADDED, member);
        }

        return null;
    }

    @Override
    public void sendMembershipCompleteMail()
    {

    }

    @Override
    public void sendProspectiveMemberReminder()
    {

    }

    @Override
    public void sendContactDetailsReminder()
    {

    }

    @Override
    public void sendSoDetailsReminder()
    {

    }

    @Override
    public void sendSoDetailsToMember()
    {

    }
}
