package uk.org.nottinghack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import uk.org.nottinghack.domain.EmailTemplate;
import uk.org.nottinghack.domain.GroupRole;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.service.EmailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class EmailServiceImpl implements EmailService
{
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final String DEFAULT_SEND_FROM;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine,
                            @Value("${hms.email.from}") String sendFrom)
    {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.DEFAULT_SEND_FROM = sendFrom;
    }

    @Override
    public void send(EmailTemplate template, Member member)
    {
        // TODO: use GroupRole to figure out if the email goes to the member or a group email

        final Context ctx = new Context();
        ctx.setVariable("member", member);

        final String htmlContent = this.templateEngine.process("email/" + template.getTemplateName(), ctx);

        sendInternal(member.getEmail(), DEFAULT_SEND_FROM, template.getSubject(), htmlContent);
    }

    private void sendInternal(String to, String from, String subject, String body)
    {
        // Prepare message using a Spring helper
        final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        final MimeMessageHelper message; // true = multipart

        try {
            message = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            message.setSubject(subject);
            message.setFrom(from);
            message.setTo(to);
            message.setText(body, true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessage);
    }
}
