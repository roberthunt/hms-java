package uk.org.nottinghack.domain;

/**
 * The idea behind this is we want to be able to send notifications about new members registering to the membership
 * admin team. We could just say that the emails go to "membership@nottinghack.org.uk" but this is not very flexible and
 * there may be cases where we need to notify more than one group of people. By creating a "role" we can then assign
 * this to any groups we like at runtime so any group can become a membership admin team by simply having the
 * MEMBERSHIP_ADMIN role assigned to them. It also avoid other hacky methods such as adding a "isMembershipAdmin" flag
 * to the group for example.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum GroupRole
{
    NONE,
    MEMBERSHIP_ADMIN
}
