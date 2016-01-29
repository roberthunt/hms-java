package uk.org.nottinghack.domain;


import uk.org.nottinghack.domain.converter.RfidTagStateAttributeConverter;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 *
 * @see RfidTagStateAttributeConverter
 */
public enum RfidTagState
{
    ACTIVE(10, "Active"),
    EXPIRED(20, "Expired"),
    LOST(30, "Lost");

    private final int id;
    private final String name;

    RfidTagState(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
}
