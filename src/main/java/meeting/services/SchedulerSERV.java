package meeting.services;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import meeting.entity.DetailsOfMeeting;
import meeting.mailSender.MailRequest;
import meeting.mailSender.MailSENDER;
import meeting.repo.MeetingScheduleREPO;

@Service
public class SchedulerSERV {

	@Autowired
	MailSENDER mail;

	@Autowired
	MeetingScheduleREPO meetingScheduleREPO;

	Duration duration;
	LocalTime currenTime, convertedTime, finalConvertedTime;

	String startTimeFromEntity, currentDate, meetingWithEmail, createdByEmail, body, subject, createdBy, meetingWith,
			startTime, endTime, description;
	SimpleDateFormat formateDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	int hrs, mins, sec;
	long gapBewteenTime;
	Boolean emailStatus;

	@Scheduled(initialDelay = 5000, fixedDelay = 60000)
	@EventListener(ApplicationReadyEvent.class)
	public void schedulingMail() {
		System.out.println("Scheduler Started");

		Date date = new Date();

		currentDate = formateDateFormat.format(date);
		System.out.println(currentDate);

		currenTime = LocalTime.now();
		startTimeFromEntity = null;
		convertedTime = null;
		System.out.println(currenTime);

		List<DetailsOfMeeting> listOfTodaysMeetings = meetingScheduleREPO.findTodaysMeetings(currentDate);

		System.out.println(listOfTodaysMeetings);

		for (DetailsOfMeeting detailsOfMeeting : listOfTodaysMeetings) {
			startTimeFromEntity = detailsOfMeeting.getStartTime();
			emailStatus = detailsOfMeeting.getMailSentStatus();

			System.out.println("StartTimeFromEntity=" + startTimeFromEntity);
			convertedTime = LocalTime.parse(startTimeFromEntity);
			hrs = convertedTime.getHour();
			mins = convertedTime.getMinute();
			sec = convertedTime.getSecond();
			meetingWith = detailsOfMeeting.getMeetingWith();
			createdBy = detailsOfMeeting.getCreatedBy();
			startTime = detailsOfMeeting.getStartTime();
			endTime = detailsOfMeeting.getEndTime();
			createdByEmail = detailsOfMeeting.getCreatedByEmail();
			meetingWithEmail = detailsOfMeeting.getMeetingWithEmail();

			meetingWithEmail = detailsOfMeeting.getMeetingWithEmail();

			finalConvertedTime = LocalTime.of(hrs, mins, sec);
			duration = Duration.between(currenTime, finalConvertedTime);
			gapBewteenTime = duration.toMinutes();

			System.out.println("GapBetweenCurrentTime and entityTime=" + gapBewteenTime);
			System.out.println(createdBy + meetingWith + startTime + endTime);
			System.out.println("Email Status=" + emailStatus);

			if (detailsOfMeeting.getMeetingDate().equals(currentDate) && gapBewteenTime <= 10 && gapBewteenTime >= 0
					&& emailStatus == false) {
				System.out.println("Meetings Fetched");
//				mail.sendEmail(createdByEmail,meetingWithEmail, "This is Subject", "This is Sample Body Mail");

				MailRequest request = new MailRequest();
				request.setName(createdBy);
				request.setFrom(createdByEmail);
				request.setTo(meetingWithEmail);
				request.setSubject("This is Subject");

				Map<String, Object> model = new HashMap<>();
				model.put("Name", request.getName());
				model.put("Location", "Pune, INDIA");

				mail.sendTemplateEmail(request, model);
				detailsOfMeeting.setMailSentCount(detailsOfMeeting.getMailSentCount()+1);
				System.out.println("detailsOfMeeting.getMailSentCount()--"+detailsOfMeeting.getMailSentCount());
				if(detailsOfMeeting.getMailSentCount()>1) {
//					meetingScheduleREPO.updateMailSentStatus(createdBy, meetingWith, startTime, endTime);
					detailsOfMeeting.setMailSentStatus(true);
				}
				
				meetingScheduleREPO.save(detailsOfMeeting);
				
			}
		}

	}

}