package uk.org.nottinghack.domain.tool;

import uk.org.nottinghack.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "tl_members_tools")
public class MemberTool
{
    @Id
    @GeneratedValue
    @Column(name = "member_tool_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id_induct")
    private Member inductor;

    @Column(name = "mt_date_inducted")
    private LocalDateTime dateInducted;

    @Column(name = "mt_access_level")
    @Enumerated(EnumType.STRING)
    private ToolUserLevel accessLevel;

    public MemberTool()
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

    public Tool getTool()
    {
        return tool;
    }

    public void setTool(Tool tool)
    {
        this.tool = tool;
    }

    public Member getInductor()
    {
        return inductor;
    }

    public void setInductor(Member inductor)
    {
        this.inductor = inductor;
    }

    public LocalDateTime getDateInducted()
    {
        return dateInducted;
    }

    public void setDateInducted(LocalDateTime dateInducted)
    {
        this.dateInducted = dateInducted;
    }

    public ToolUserLevel getAccessLevel()
    {
        return accessLevel;
    }

    public void setAccessLevel(ToolUserLevel accessLevel)
    {
        this.accessLevel = accessLevel;
    }
}
