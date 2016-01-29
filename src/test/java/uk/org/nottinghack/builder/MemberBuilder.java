package uk.org.nottinghack.builder;

import uk.org.nottinghack.domain.*;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MemberBuilder
{
    int id;
    String firstname;
    String surname;
    String email;
    LocalDate joinDate;
    String unlockText;
    int balance;
    int creditLimit;
    MemberStatus status;
    String username;
    Account account = new AccountBuilder().build();
    String firstAddressLine;
    String secondAddressLine;
    String city;
    String postcode;
    String contactNumber;
    Set<Pin> pins;
    Set<Group> groups;

    public MemberBuilder id(int id)
    {
        this.id = id;
        return this;
    }

    public MemberBuilder firstname(String firstname)
    {
        this.firstname = firstname;
        return this;
    }

    public MemberBuilder surname(String surname)
    {
        this.surname = surname;
        return this;
    }

    public MemberBuilder email(String email)
    {
        this.email = email;
        return this;
    }

    public MemberBuilder joinDate(LocalDate joinDate)
    {
        this.joinDate = joinDate;
        return this;
    }

    public MemberBuilder unlockText(String unlockText)
    {
        this.unlockText = unlockText;
        return this;
    }

    public MemberBuilder balance(int balance)
    {
        this.balance = balance;
        return this;
    }

    public MemberBuilder creditLimit(int creditLimit)
    {
        this.creditLimit = creditLimit;
        return this;
    }

    public MemberBuilder status(MemberStatus status)
    {
        this.status = status;
        return this;
    }

    public MemberBuilder username(String username)
    {
        this.username = username;
        return this;
    }

    public MemberBuilder account(Account account)
    {
        this.account = account;
        return this;
    }

    public MemberBuilder account(AccountBuilder accountBuilder)
    {
        this.account = accountBuilder.build();
        return this;
    }

    public MemberBuilder firstAddressLine(String firstAddressLine)
    {
        this.firstAddressLine = firstAddressLine;
        return this;
    }

    public MemberBuilder secondAddressLine(String secondAddressLine)
    {
        this.secondAddressLine = secondAddressLine;
        return this;
    }

    public MemberBuilder city(String city)
    {
        this.city = city;
        return this;
    }

    public MemberBuilder postcode(String postcode)
    {
        this.postcode = postcode;
        return this;
    }

    public MemberBuilder contactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
        return this;
    }

    public MemberBuilder pins(Set<Pin> pins)
    {
        this.pins = pins;
        return this;
    }

    public MemberBuilder groups(Set<Group> groups)
    {
        this.groups = groups;
        return this;
    }

    public Member build()
    {
        return new Member(id, firstname, surname, email, joinDate, unlockText, balance, creditLimit, status, username,
                account, firstAddressLine, secondAddressLine, city, postcode, contactNumber, pins, groups);
    }
}
