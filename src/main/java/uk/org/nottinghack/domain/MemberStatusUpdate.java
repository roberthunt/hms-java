package uk.org.nottinghack.domain;

import uk.org.nottinghack.domain.converter.MemberStatusAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "status_updates")
public class MemberStatusUpdate
{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Member admin; // the member who performed the status update

    @Convert(converter = MemberStatusAttributeConverter.class)
    @Column(name = "old_status")
    private MemberStatus oldStatus;

    @Convert(converter = MemberStatusAttributeConverter.class)
    @Column(name = "new_status")
    private MemberStatus newStatus;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public MemberStatusUpdate()
    {
        // default no-arg constructor
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public Member getAdmin()
    {
        return admin;
    }

    public void setAdmin(Member admin)
    {
        this.admin = admin;
    }

    public MemberStatus getOldStatus()
    {
        return oldStatus;
    }

    public void setOldStatus(MemberStatus oldStatus)
    {
        this.oldStatus = oldStatus;
    }

    public MemberStatus getNewStatus()
    {
        return newStatus;
    }

    public void setNewStatus(MemberStatus newStatus)
    {
        this.newStatus = newStatus;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }

    public String getDescription()
    {
        String description = "";

        if (newStatus == MemberStatus.CURRENT_MEMBER)
        {
            description = "Membership Granted";
        }
        else if (newStatus == MemberStatus.EX_MEMBER)
        {
            description = "Membership Revoked";
        }
        else if (newStatus == MemberStatus.WAITING_FOR_CONTACT_DETAILS)
        {
            if (oldStatus == MemberStatus.WAITING_FOR_MEMBERSHIP_ADMIN_TO_APPROVE_CONTACT_DETAILS)
            {
                description = "Contact Details Rejected";
            }
        }

        return description;
    }
}
