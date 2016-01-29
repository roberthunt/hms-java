package uk.org.nottinghack.domain.tool;

import uk.org.nottinghack.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "tl_tool_usages")
public class ToolUsage
{
    @Id
    @GeneratedValue
    @Column(name = "usage_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tool_id")
    private Tool tool;

    @Column(name = "usage_start")
    private LocalDateTime start;

    @Column(name = "usage_duration")
    private int duration;

    @Column(name = "usage_active_time")
    private int activeTime;

    @Column(name = "usage_status")
    @Enumerated(EnumType.STRING)
    private ToolUsageStatus status;

    public ToolUsage()
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

    public LocalDateTime getStart()
    {
        return start;
    }

    public void setStart(LocalDateTime start)
    {
        this.start = start;
    }

    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    public int getActiveTime()
    {
        return activeTime;
    }

    public void setActiveTime(int activeTime)
    {
        this.activeTime = activeTime;
    }

    public ToolUsageStatus getStatus()
    {
        return status;
    }

    public void setStatus(ToolUsageStatus status)
    {
        this.status = status;
    }
}
