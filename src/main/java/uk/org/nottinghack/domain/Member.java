package uk.org.nottinghack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uk.org.nottinghack.domain.converter.MemberStatusAttributeConverter;
import uk.org.nottinghack.security.ReturnNullOnAccessDenied;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "members")
public class Member implements UserDetails, Serializable
{
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final int FIRSTNAME_MAX_LENGTH = 100;
    public static final int SURNAME_MAX_LENGTH = 100;
    public static final int EMAIL_MAX_LENGTH = 100;
    public static final int UNLOCK_TEXT_MAX_LENGTH = 95;
    public static final int ADDRESS_LINE_MAX_LENGTH = 100;

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private int id;

    @Column(name = "firstname", length = FIRSTNAME_MAX_LENGTH)
    @Size(min = 1, max = FIRSTNAME_MAX_LENGTH, message = "Cannot be more than " + FIRSTNAME_MAX_LENGTH + " characters long")
    private String firstname;

    @Column(name = "surname", length = SURNAME_MAX_LENGTH)
    @Size(min = 1, max = SURNAME_MAX_LENGTH, message = "Cannot be more than " + SURNAME_MAX_LENGTH + " characters long")
    private String surname;

    @Column(name = "email", length = EMAIL_MAX_LENGTH, nullable = false)
    @Size(min = 1, max = EMAIL_MAX_LENGTH, message = "{CUST}")
    private String email;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Column(name = "unlock_text", length = UNLOCK_TEXT_MAX_LENGTH)
    @Size(max = UNLOCK_TEXT_MAX_LENGTH, message = "Cannot be more than " + UNLOCK_TEXT_MAX_LENGTH + " characters long")
    private String unlockText;

    @Column(name = "balance", nullable = false)
    private Integer balance;

    @Column(name = "credit_limit", nullable = false)
    private Integer creditLimit;

    @Convert(converter = MemberStatusAttributeConverter.class)
    @Column(name = "member_status", nullable = false)
    private MemberStatus status;

    @Column(name = "username", length = USERNAME_MAX_LENGTH)
    @Size(min = 1, max = USERNAME_MAX_LENGTH, message = "Cannot be more than " + USERNAME_MAX_LENGTH + " characters long")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "address_1", length = 100)
    @Size(max = ADDRESS_LINE_MAX_LENGTH, message = "Cannot be more than " + ADDRESS_LINE_MAX_LENGTH + " characters long")
    private String firstAddressLine;

    @Column(name = "address_2", length = 100)
    @Size(max = ADDRESS_LINE_MAX_LENGTH, message = "Cannot be more than " + ADDRESS_LINE_MAX_LENGTH + " characters long")
    private String secondAddressLine;

    @Column(name = "address_city", length = 100)
    @Size(max = ADDRESS_LINE_MAX_LENGTH, message = "Cannot be more than " + ADDRESS_LINE_MAX_LENGTH + " characters long")
    private String city;

    @Column(name = "address_postcode", length = 100)
    @Size(max = ADDRESS_LINE_MAX_LENGTH, message = "Cannot be more than " + ADDRESS_LINE_MAX_LENGTH + " characters long")
    private String postcode;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;

    @OrderBy
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", referencedColumnName="member_id")
    private Set<Pin> pins;

    @OrderBy
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id", referencedColumnName="member_id")
    private List<RfidTag> cards;

    @OrderBy
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "member_group",
               joinColumns = {@JoinColumn(name="member_id", referencedColumnName="member_id")},
               inverseJoinColumns = {@JoinColumn(name="grp_id", referencedColumnName="grp_id")})
    private Set<Group> groups = Collections.EMPTY_SET;

    // transient fields are generated at runtime and not part of the persistent entity
    @Transient
    private Set<GrantedAuthority> grantedAuthorities;

    public Member()
    {
        // default no-arg constructor
    }

    public Member(int id, String firstname, String surname, String email, LocalDate joinDate, String unlockText,
                  Integer balance, Integer creditLimit, MemberStatus status, String username, Account account,
                  String firstAddressLine, String secondAddressLine, String city, String postcode, String contactNumber,
                  Set<Pin> pins, Set<Group> groups)
    {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
        this.joinDate = joinDate;
        this.unlockText = unlockText;
        this.balance = balance;
        this.creditLimit = creditLimit;
        this.status = status;
        this.username = username;
        this.account = account;
        this.firstAddressLine = firstAddressLine;
        this.secondAddressLine = secondAddressLine;
        this.city = city;
        this.postcode = postcode;
        this.contactNumber = contactNumber;
        this.pins = pins;
        this.groups = groups;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("this.isCurrentOrEx()")
    public LocalDate getJoinDate()
    {
        return joinDate;
    }

    @PreAuthorize("this.isCurrentOrEx()")
    public void setJoinDate(LocalDate joinDate)
    {
        this.joinDate = joinDate;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("this.isCurrentOrEx()")
    public String getUnlockText()
    {
        return unlockText;
    }

    @PreAuthorize("this.isCurrentOrEx()")
    public void setUnlockText(String unlockText)
    {
        this.unlockText = unlockText;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("this.isCurrentOrEx() and (hasAuthority('" + Permission.VIEW_MEMBER_ADMIN_FEATURES + "') or principal.id == this.id)")
    public Integer getBalance()
    {
        return balance;
    }

    public void setBalance(Integer balance)
    {
        this.balance = balance;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("this.isCurrentOrEx() and (hasAuthority('" + Permission.VIEW_MEMBER_ADMIN_FEATURES + "') or principal.id == this.id)")
    public Integer getCreditLimit()
    {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit)
    {
        this.creditLimit = creditLimit;
    }

    public MemberStatus getStatus()
    {
        return status;
    }

    public void setStatus(MemberStatus status)
    {
        this.status = status;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_PERSONAL_DETAILS + "') or principal.id == this.id")
    public String getFirstAddressLine()
    {
        return firstAddressLine;
    }

    public void setFirstAddressLine(String firstAddressLine)
    {
        this.firstAddressLine = firstAddressLine;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_PERSONAL_DETAILS + "') or principal.id == this.id")
    public String getSecondAddressLine()
    {
        return secondAddressLine;
    }

    public void setSecondAddressLine(String secondAddressLine)
    {
        this.secondAddressLine = secondAddressLine;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_PERSONAL_DETAILS + "') or principal.id == this.id")
    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_PERSONAL_DETAILS + "') or principal.id == this.id")
    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_PERSONAL_DETAILS + "') or principal.id == this.id")
    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_ADMIN_FEATURES + "')")
    public Set<Pin> getPins()
    {
        return pins;
    }

    public void setPins(Set<Pin> pins)
    {
        this.pins = pins;
    }

    public List<RfidTag> getCards()
    {
        return cards;
    }

    public void setCards(List<RfidTag> cards)
    {
        this.cards = cards;
    }

    @ReturnNullOnAccessDenied
    @PreAuthorize("hasAuthority('" + Permission.VIEW_MEMBER_ADMIN_FEATURES + "')")
    public Set<Group> getGroups()
    {
        return groups;
    }

    public void setGroups(Set<Group> groups)
    {
        this.groups = groups;
    }

    /**
     * Has this member ever been a full member, this is equivalent to 'hasJoined' in HMS PHP.
     */
    public boolean isCurrentOrEx()
    {
        return status == MemberStatus.CURRENT_MEMBER || status == MemberStatus.EX_MEMBER;
    }

    // logic to determine which actions should be visible on the member page (based on member status)

    // PROSPECTIVE_MEMBER
    // - Send Membership Reminder
    //
    // WAITING_FOR_CONTACT_DETAILS
    // - Send Contact Details Reminder
    //
    // WAITING_FOR_MEMBERSHIP_ADMIN_TO_APPROVE_CONTACT_DETAILS
    // - Accept Details
    // - Reject Details
    //
    // WAITING_FOR_STANDING_ORDER_PAYMENT
    // - Send SO Details Reminder
    // - Approve Member
    //
    // CURRENT_MEMBER
    // - Revoke Membership
    // - Send SO Details Reminder
    // - Resend Welcome Email
    //
    // EX_MEMBER
    // - Reinstate Membership
    //
    //
    //    <th:block th:switch="${members.status}">
    //        <th:block th:case="${T(uk.org.nottinghack.domain.MemberStatus.CURRENT_MEMBER)}">Current Member</th:block>
    //    </th:block>

    // Spring Security User Details

    /**
     * Note: this caches the permissions, the member will need to log out and back in again for permission changes to
     * take effect as the member is stored in the security context.
     * @return a collection of permissions that the member has been granted via its group.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        if (grantedAuthorities == null)
        {
            // lazy init
            grantedAuthorities = new HashSet<>();

            // inspect each group the member belongs to for permissions
            for (Group group : groups)
            {
                for (String permission : group.getPermissions())
                {
                    grantedAuthorities.add(new SimpleGrantedAuthority(permission.trim()));
                }
            }
        }
        // TODO: TESTING ONLY
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_OTHER_MEMBERS));
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_MEMBER_ADMIN_FEATURES));
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_EMAILS));
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_OTHER_RFID_CARDS));
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_MEMBER_PERSONAL_DETAILS));
////        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_MEMBER_FINANCES));
//
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.VIEW_TOOLS));
//        grantedAuthorities.add(new SimpleGrantedAuthority(Permission.ADD_TOOL));

        return grantedAuthorities;
    }

    /**
     * Access of the member's password is unsupported.
     * @throws UnsupportedOperationException
     */
    // TODO: should not need JsonIgnore, this is a quick fix for development where we send the member as JSON to the client
    @JsonIgnore
    @Override
    public String getPassword()
    {
        return "";
        //throw new UnsupportedOperationException("Access of the member's password is unsupported");
    }

    /**
     * HMS does not expire member's accounts at the moment.
     * @return true as member's accounts do not expire.
     */
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * HMS does not lock member's accounts at the moment.
     * @return true as member's accounts cannot be locked.
     */
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * HMS does not expire member's credentials at the moment.
     * @return true as member's credentials do not expire.
     */
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * HMS does not have the concept of "disabled" members at the moment, anyone with an account is able to login. We
     * could consider members with the status "Ex Member" as "disabled" but we probably want them to still be able to
     * login so they can setup payment again, check balances etc.
     * @return true as members cannot be disabled.
     */
    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
