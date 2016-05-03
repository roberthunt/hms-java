package uk.org.nottinghack.domain.dto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class GroupDto implements Comparable<GroupDto>
{
    private int id;
    private String description;

    public GroupDto()
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int compareTo(GroupDto o)
    {
        return id > o.id ? 1 : id < o.id ? -1 : 0;
    }
}
