package uk.org.nottinghack.service.impl;


import uk.org.nottinghack.domain.MailingList;
import uk.org.nottinghack.service.MailinglistService;

import java.util.ArrayList;
import java.util.List;

/**
 * * * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class DummyMailingListService implements MailinglistService
{
    @Override
    public List<MailingList> getMailingLists()
    {
        List<MailingList> mailingLists = new ArrayList<>();
        mailingLists.add(new MailingList("1", "Member Announcements"));
        mailingLists.add(new MailingList("2", "Workshop Announcements"));
        mailingLists.add(new MailingList("3", "Monthly Newsletter"));
        return mailingLists;
    }

    @Override
    public MailingList getMailingList(String listId)
    {
        return null;
    }

    @Override
    public List<String> getSubscribers(String listId)
    {
        return null;
    }

    @Override
    public boolean subscribe(String listId, String email)
    {
        return false;
    }

    @Override
    public boolean unsubscribe(String listId, String email)
    {
        return false;
    }

    @Override
    public boolean isSubscriber(String listId, String email)
    {
        return false;
    }
}
