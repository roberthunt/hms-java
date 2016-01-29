package uk.org.nottinghack.domain;

import com.ecwid.mailchimp.MailChimpClient;
import com.ecwid.mailchimp.MailChimpException;
import com.ecwid.mailchimp.method.v1_3.list.ListsMethod;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.org.nottinghack.Application;

import javax.transaction.Transactional;
import java.io.IOException;

/**
 * Created by Rob on 22/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class MCAPITest
{
    @Test
    public void testApi()
    {
        MailChimpClient mailChimpClient = new MailChimpClient();


        ListsMethod listsMethod = new ListsMethod();
        listsMethod.apikey = "5157ccf6daa987e19296d8eb1fd478e0-us11";

        try
        {
            System.out.println(mailChimpClient.execute(listsMethod));
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (MailChimpException e)
        {
            e.printStackTrace();
        }
    }
}
