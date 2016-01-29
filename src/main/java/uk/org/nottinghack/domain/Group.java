package uk.org.nottinghack.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Rob on 13/06/2015.
 */
@Entity
@Table(name = "grp")
public class Group implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name = "grp_id")
    private int id;

    @Column(name = "grp_description", length = 200)
    private String description;

    @ManyToMany(mappedBy = "groups")
    private Set<Member> members;

    @ElementCollection
    @CollectionTable(name = "group_permissions", joinColumns = @JoinColumn(name = "grp_id"))
    @Column(name = "permission_code", nullable = false)
    private Set<String> permissions;

    public Group()
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

    public Set<Member> getMembers()
    {
        return members;
    }

    public void setMembers(Set<Member> members)
    {
        this.members = members;
    }

    public Set<String> getPermissions()
    {
        return permissions;
    }

    public void setPermissions(Set<String> permissions)
    {
        this.permissions = permissions;
    }

    //TODO: this might change if we point to set to the Permission entity rather than a Set of Strings
    public boolean hasPermission(String permissionCode)
    {
        return permissions.contains(permissionCode);
    }
}
