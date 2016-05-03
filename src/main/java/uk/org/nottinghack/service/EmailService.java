package uk.org.nottinghack.service;

import uk.org.nottinghack.domain.EmailTemplate;
import uk.org.nottinghack.domain.Member;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface EmailService
{
    void send(EmailTemplate template, Member member);
}
