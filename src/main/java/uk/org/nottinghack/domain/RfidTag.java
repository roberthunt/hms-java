package uk.org.nottinghack.domain;

import uk.org.nottinghack.domain.converter.RfidTagStateAttributeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "rfid_tags")
public class RfidTag implements Serializable
{
    @Id
    @Column(name = "rfid_serial", length = 50)
    @Pattern(regexp = "\\d+", message = "Card serial must be a number")
    @Size(min = 1, max = 50, message = "Card serial number must be between 1 and 50 digits long")
    private String serial;

    @Convert(converter = RfidTagStateAttributeConverter.class)
    @Column(name = "state", nullable = false)
    @NotNull
    private RfidTagState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "friendly_name", length = 128)
    @Size(min = 1, max = 128, message = "Cannot be more than 128 characters long")
    private String friendlyName;

    @Column(name = "last_used")
    private LocalDateTime lastUsed;

    public RfidTag()
    {
        // default no-arg constructor
    }

    public String getSerial()
    {
        return serial;
    }

    public void setSerial(String serial)
    {
        this.serial = serial;
    }

    public RfidTagState getState()
    {
        return state;
    }

    public void setState(RfidTagState state)
    {
        this.state = state;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public String getFriendlyName()
    {
        return friendlyName;
    }

    public void setFriendlyName(String friendlyName)
    {
        this.friendlyName = friendlyName;
    }

    public LocalDateTime getLastUsed()
    {
        return lastUsed;
    }

    public void setLastUsed(LocalDateTime lastUsed)
    {
        this.lastUsed = lastUsed;
    }
}
