package meeting.mailSender;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class MailSENDER {

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	Configuration configuration;
	
	Integer mailSendCount=0;

	public void sendEmail(String createdByEmail, String meetingWith, String subjeString, String body) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("skankatti@gmail.com");
		message.setTo(meetingWith);
		message.setCc(createdByEmail);

		message.setText(body);
		message.setSubject(subjeString);

		mailSender.send(message);

		System.out.println("Mail Sent");
	}

	public MailResponse sendTemplateEmail(MailRequest request, Map<String, Object> model) {
		MailResponse response = new MailResponse();
		MimeMessage message = mailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());

			// add attachment
//			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

			Template t = configuration.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo(request.getTo());
			helper.setCc(request.getFrom());
			helper.setText(html, true);
			helper.setSubject(request.getSubject());
			helper.setFrom(request.getFrom());
			mailSender.send(message);

			response.setMessage("mail send to : " + request.getTo());
		
			response.setStatus(Boolean.TRUE);
		} 
		catch (MessagingException | IOException | TemplateException e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}
		return response;

	}

}
