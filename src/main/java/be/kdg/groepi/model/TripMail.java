package be.kdg.groepi.model;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


/**
 * Created with IntelliJ IDEA.
 * User: Tim
 * Date: 19/02/13
 * Time: 15:41
 * To change this template use File | Settings | File Templates.
 */
public class TripMail {
    private MailSender mailSender;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String from, String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
