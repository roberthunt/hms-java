package uk.org.nottinghack.service;

/**
 * Created by rob on 05/11/15.
 */
public interface EmailService
{
    void send(String to, String subject, String body);
}
