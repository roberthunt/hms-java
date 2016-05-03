package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.MailingList;

import java.util.List;

/**
 * Mailing list service interface.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MailinglistService
{
    List<MailingList> getMailingLists();

    MailingList getMailingList(String listId);

    List<String> getSubscribers(String listId);

    boolean subscribe(String listId, String email);

    boolean unsubscribe(String listId, String email);

    boolean isSubscriber(String listId, String email);
}
