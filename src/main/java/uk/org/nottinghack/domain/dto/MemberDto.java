package uk.org.nottinghack.domain.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MemberDto
{
    private int id;
    private String firstname;
    private String surname;
    private String email;
    private LocalDate joinDate;
    private String unlockText;
    private Integer balance;
    private Integer creditLimit;
    private MemberStatusDto status;
    private String username;
    private String paymentReference;
    private String firstAddressLine;
    private String secondAddressLine;
    private String city;
    private String postcode;
    private String contactNumber;
    private PinDto pin;
    private List<RfidTagDto> cards;
    private SortedSet<GroupDto> groups;
    private List<String> addressLines;

    public MemberDto()
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

    public LocalDate getJoinDate()
    {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate)
    {
        this.joinDate = joinDate;
    }

    public String getUnlockText()
    {
        return unlockText;
    }

    public void setUnlockText(String unlockText)
    {
        this.unlockText = unlockText;
    }

    public Integer getBalance()
    {
        return balance;
    }

    public void setBalance(Integer balance)
    {
        this.balance = balance;
    }

    public Integer getCreditLimit()
    {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit)
    {
        this.creditLimit = creditLimit;
    }

    public MemberStatusDto getStatus()
    {
        return status;
    }

    public void setStatus(MemberStatusDto status)
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

    public String getPaymentReference()
    {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference)
    {
        this.paymentReference = paymentReference;
    }

    public String getFirstAddressLine()
    {
        return firstAddressLine;
    }

    public void setFirstAddressLine(String firstAddressLine)
    {
        this.firstAddressLine = firstAddressLine;
    }

    public String getSecondAddressLine()
    {
        return secondAddressLine;
    }

    public void setSecondAddressLine(String secondAddressLine)
    {
        this.secondAddressLine = secondAddressLine;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public PinDto getPin()
    {
        return pin;
    }

    public void setPin(PinDto pin)
    {
        this.pin = pin;
    }

    public List<RfidTagDto> getCards()
    {
        return cards;
    }

    public void setCards(List<RfidTagDto> cards)
    {
        this.cards = cards;
    }

    public Set<GroupDto> getGroups()
    {
        return groups;
    }

    public void setGroups(SortedSet<GroupDto> groups)
    {
        this.groups = groups;
    }

    // utility methods

    public double getBalanceInPounds()
    {
        return getBalance() / 100;
    }

    public double getCreditLimitInPounds()
    {
        return getCreditLimit() / 100;
    }

    /**
     * Gets the member's username if it has been set otherwise returns their email address.
     * @return
     */
    public String getUsernameOrEmail()
    {
        return getUsername() != null ? getUsername() : getEmail();
    }

    /**
     * Gets the member's full name by concatenating the firstname and surname.
     * @return full members name.
     */
    public String getFullName()
    {
        String firstname = getFirstname() != null ? getFirstname() : "";
        String surname = getSurname() != null ? getSurname() : "";
        return (firstname + surname).isEmpty() ? "" : firstname + " " + surname;
    }

    /**
     * Gets the best available name for the member by concatenating the firstname and surname if the firstname is
     * present otherwise the member's email address is returned.
     * @return best members name.
     */
    public String getBestName()
    {
        return getFullName().isEmpty() ? getEmail() : getFullName();
    }

    /**
     * Returns true if the member has a non-null and non-empty contact number.
     * @return
     */
    public boolean hasContactNumber()
    {
        if (getContactNumber() != null && getContactNumber().trim().length() > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Returns an array of address lines by assembling non-null address components.
     * @return
     */
    public String[] getAddressLines()
    {
        if (addressLines == null)
        {
            addressLines = new ArrayList<>();

            if (getFirstAddressLine() != null)
            {
                addressLines.add(firstAddressLine);
            }
            if (getSecondAddressLine() != null)
            {
                addressLines.add(secondAddressLine);
            }
            if (getCity() != null)
            {
                addressLines.add(city);
            }
            if (getPostcode() != null)
            {
                addressLines.add(postcode);
            }
        }

        return addressLines.toArray(new String[0]);
    }

    /**
     * Returns true if there is at least one address component for this member.
     * @return
     */
    public boolean hasAddress()
    {
        return getAddressLines().length > 0;
    }
}
