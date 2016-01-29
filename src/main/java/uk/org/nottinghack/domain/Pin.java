package uk.org.nottinghack.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by Rob on 09/01/2015.
 */
@Entity
@Table(name = "pins")
public class Pin implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name = "pin_id")
    private int id;

    @Column(name = "pin", length = 12)
    private String pin;

    @Column(name = "date_added")
    private LocalDateTime added;

    @Column(name = "expiry")
    private LocalDateTime expiry;

    // TODO: embedded type or enum
    @Column(name = "state")
    private Integer state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Pin()
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

    public String getPin()
    {
        return pin;
    }

    public void setPin(String pin)
    {
        this.pin = pin;
    }

    public LocalDateTime getAdded()
    {
        return added;
    }

    public void setAdded(LocalDateTime added)
    {
        this.added = added;
    }

    public LocalDateTime getExpiry()
    {
        return expiry;
    }

    public void setExpiry(LocalDateTime expiry)
    {
        this.expiry = expiry;
    }

    public Integer getState()
    {
        return state;
    }

    public void setState(Integer state)
    {
        this.state = state;
    }

//    public Member getMember()
//    {
//        return member;
//    }

    public void setMember(Member member)
    {
        this.member = member;
    }
}
