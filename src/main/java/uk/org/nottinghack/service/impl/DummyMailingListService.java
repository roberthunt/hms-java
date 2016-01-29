package uk.org.nottinghack.service.impl;

import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.MailingList;
import uk.org.nottinghack.service.MailinglistService;

import java.util.ArrayList;
import java.util.List;

/**
 * * * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class DummyMailingListService implements MailinglistService
{
    @Override
    public List<MailingList> getMailingLists()
    {
        List<MailingList> mailingLists = new ArrayList<>();
        mailingLists.add(new MailingList(1, "Member Announcements"));
        mailingLists.add(new MailingList(2, "Workshop Announcements"));
        mailingLists.add(new MailingList(3, "Monthly Newsletter"));
        return mailingLists;
    }
}
