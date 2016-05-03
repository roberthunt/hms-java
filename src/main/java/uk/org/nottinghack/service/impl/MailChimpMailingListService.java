package uk.org.nottinghack.service.impl;

import com.mailchimp.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import uk.org.nottinghack.domain.MailingList;
import uk.org.nottinghack.service.MailinglistService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class MailChimpMailingListService implements MailinglistService
{
    private final RestTemplate restTemplate;

    // TODO: inject from config
    private static final String API_KEY = "";
    private static final String API_URL = "https://us5.api.mailchimp.com/3.0/";

    @Autowired
    public MailChimpMailingListService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<MailingList> getMailingLists()
    {
        MailChimpListCollection mailChimpLists = doGet("/lists", MailChimpListCollection.class);
        List<MailingList> mailingLists = mailChimpLists.getLists()
                .stream()
                .map(this::mailChimpListToMailingList)
                .collect(Collectors.toList());

        return mailingLists;
    }

    @Override
    public MailingList getMailingList(String listId)
    {
        MailChimpList mailChimpList = doGet("/lists/" + listId, MailChimpList.class);
        return mailChimpListToMailingList(mailChimpList);
    }

    @Override
    public List<String> getSubscribers(String listId)
    {
        // TODO: MailChimp API uses paging, we can either
        // TODO: a) request a huge page size such as 2000
        // TODO: b) issue a request to see how many results there are then query a second time
        // TODO: c) request a few then ask for the next page until we hit the end
        MailChimpMemberCollection mailingListMembers = doGet("/lists/" + listId + "/members?fields=members.email_address,total_items&count=100", MailChimpMemberCollection.class);
        return mailingListMembers.getMembers()
                .stream()
                .map(MailChimpMember::getEmailAddress)
                .collect(Collectors.toList());
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
        String subscriberHash = DigestUtils.md5DigestAsHex(email.toLowerCase().getBytes());

        try
        {
            doGet("/lists/" + listId + "/members/" + subscriberHash, MailChimpMember.class);
        }
        catch (HttpClientErrorException e)
        {
            if (HttpStatus.NOT_FOUND.equals(e.getStatusCode()))
            {
                return false;
            }
        }

        return true;
    }

    private <T> T doGet(String url, Class<T> responseType)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","apikey " + API_KEY);
        HttpEntity<String> entity = new HttpEntity<>("", headers);

        ResponseEntity<T> response = restTemplate.exchange(API_URL + url, HttpMethod.GET, entity, responseType);

        return response.getBody();
    }

    private MailingList mailChimpListToMailingList(MailChimpList mailChimpList)
    {
        MailingList mailingList = new MailingList(mailChimpList.getId(), mailChimpList.getName());
        mailingList.setCreated(mailChimpList.getDateCreated());

        // add stats
        MailChimpStats stats = mailChimpList.getStats();
        mailingList.setSubscribers(stats.getMemberCount());
        mailingList.setNewSubscribersSinceLastMail(stats.getMemberCountSinceSend());
        mailingList.setUnsubscribers(stats.getUnsubscribeCount());
        mailingList.setNewUnsubscribersSinceLastMail(stats.getUnsubscribeCountSinceSend());
        mailingList.setMailsSent(stats.getCampaignCount());

        return mailingList;
    }
}
