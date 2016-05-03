package uk.org.nottinghack.domain;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum EmailTemplate
{
    NOTIFY_ADMINS_MEMBER_ADDED("notify_admins_member_added", "New Prospective Member Notification", GroupRole.MEMBERSHIP_ADMIN),
    NOTIFY_ADMINS_CHECK_CONTACT_DETAILS("notify_admins_check_contact_details", "New Member Contact Details"),
    NOTIFY_ADMINS_PAYMENT_INCOMING("notify_admins_payment_incoming", "Impending Payment"),
    NOTIFY_ADMINS_MEMBER_APPROVED("notify_admins_member_approved", "Member Approved"),
    NOTIFY_ADMINS_MEMBER_REINSTATED("notify_admins_member_reinstated", "Member Reinstated"),
    TO_PROSPECTIVE_MEMBER("to_prospective_member", "Welcome to Nottingham Hackspace"),
    TO_MEMBER_MEMBERSHIP_REMINDER("to_member_membership_reminder", ""),
    TO_MEMBER_CONTACT_DETAILS_REMINDER("to_member_contact_details_reminder", "Membership Info"),
    TO_MEMBER_POST_CONTACT_UPDATE("to_member_post_contact_update", "Contact Information Completed"),
    TO_MEMBER_CONTACT_DETAILS_REJECTED("to_member_contact_details_rejected", "Issue With Contact Information"),
    TO_MEMBER_SO_DETAILS("to_member_so_details", "Bank Details"),
    TO_MEMBER_ACCESS_DETAILS("to_member_access_details", "Membership Complete"),
    TO_MEMBER_ACCESS_DETAILS_REINSTATED("to_member_access_details_reinstated", "Your Membership Has Been Reinstated"),
    FORGOT_PASSWORD("forgot_password", "Password Reset Request");

    private final String templateName;
    private final String subject;
    private final Set<GroupRole> roles;

    EmailTemplate(String templateName, String subject)
    {
        this(templateName, subject, GroupRole.NONE);
    }

    EmailTemplate(String templateName, String subject, GroupRole... roles)
    {
        this.templateName = templateName;
        this.subject = subject;
        this.roles = EnumSet.noneOf(GroupRole.class);
        this.roles.addAll(Arrays.asList(roles));
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public String getSubject()
    {
        return subject;
    }

    public Set<GroupRole> getRoles()
    {
        return roles;
    }
}
