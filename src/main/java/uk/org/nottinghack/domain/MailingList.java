package uk.org.nottinghack.domain;

import java.time.ZonedDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MailingList
{
    private final String id;
    private final String name;
    private ZonedDateTime created;
    private int subscribers;
    private int memberSubscribers;
    private int unsubscribers;
    private int newSubscribersSinceLastMail;
    private int newUnsubscribersSinceLastMail;
    private int mailsSent;

    public MailingList(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public ZonedDateTime getCreated()
    {
        return created;
    }

    public void setCreated(ZonedDateTime created)
    {
        this.created = created;
    }

    public int getSubscribers()
    {
        return subscribers;
    }

    public void setSubscribers(int subscribers)
    {
        this.subscribers = subscribers;
    }

    public int getMemberSubscribers()
    {
        return memberSubscribers;
    }

    public void setMemberSubscribers(int memberSubscribers)
    {
        this.memberSubscribers = memberSubscribers;
    }

    public int getUnsubscribers()
    {
        return unsubscribers;
    }

    public void setUnsubscribers(int unsubscribers)
    {
        this.unsubscribers = unsubscribers;
    }

    public int getNewSubscribersSinceLastMail()
    {
        return newSubscribersSinceLastMail;
    }

    public void setNewSubscribersSinceLastMail(int newSubscribersSinceLastMail)
    {
        this.newSubscribersSinceLastMail = newSubscribersSinceLastMail;
    }

    public int getNewUnsubscribersSinceLastMail()
    {
        return newUnsubscribersSinceLastMail;
    }

    public void setNewUnsubscribersSinceLastMail(int newUnsubscribersSinceLastMail)
    {
        this.newUnsubscribersSinceLastMail = newUnsubscribersSinceLastMail;
    }

    public int getMailsSent()
    {
        return mailsSent;
    }

    public void setMailsSent(int mailsSent)
    {
        this.mailsSent = mailsSent;
    }
}
