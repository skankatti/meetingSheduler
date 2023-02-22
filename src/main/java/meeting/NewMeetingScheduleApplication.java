package meeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import meeting.mailSender.MailSENDER;

@EnableScheduling
@SpringBootApplication
public class NewMeetingScheduleApplication {
	
	@Autowired
	MailSENDER mail;
	
	public static void main(String[] args) {
		SpringApplication.run(NewMeetingScheduleApplication.class, args);

	}

}
