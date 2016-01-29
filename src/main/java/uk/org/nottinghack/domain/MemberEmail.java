package uk.org.nottinghack.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "hms_emails")
public class MemberEmail
{
    @Id
    @GeneratedValue
    @Column(name = "hms_email_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public MemberEmail()
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

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }
}
