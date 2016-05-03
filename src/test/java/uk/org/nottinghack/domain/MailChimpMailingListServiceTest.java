package uk.org.nottinghack.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import uk.org.nottinghack.service.MailinglistService;
import uk.org.nottinghack.service.impl.MailChimpMailingListService;

import java.util.Collections;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MailChimpMailingListServiceTest
{
    private MailinglistService mailinglistService;

    @Before
    public void setUp()
    {
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        messageConverter.setObjectMapper(objectMapper);

        RestTemplate restTemplate = new RestTemplate(Collections.singletonList(messageConverter));

        mailinglistService = new MailChimpMailingListService(restTemplate);
    }

    @Test
    public void getMailingLists()
    {
        //mailinglistService.getMailingLists();
    }
}
