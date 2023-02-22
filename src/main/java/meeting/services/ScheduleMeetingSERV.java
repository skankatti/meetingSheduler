package meeting.services;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import meeting.entity.DetailsOfMeeting;
import meeting.exception.CustomAppException;
import meeting.exception.ResourceNotFoundException;
import meeting.payloads.ScheduleMeetingDTO;
import meeting.repo.MeetingScheduleREPO;

@Service
@Configuration
public class ScheduleMeetingSERV {

	@Autowired
	MeetingScheduleREPO meetingScheduleREPO;

	@Autowired
	DetailsOfMeeting detailsOfMeeting;

	public List<DetailsOfMeeting> getMeetingDetails() {
		return meetingScheduleREPO.findAll();
	}

	public DetailsOfMeeting saveDetailsOfMeeting(DetailsOfMeeting detailsOfMeeting) {
		LocalTime convertedStartTime = LocalTime.parse(detailsOfMeeting.getStartTime()),
				convertedEndTime = LocalTime.parse(detailsOfMeeting.getEndTime());
		Duration duration = Duration.between(convertedStartTime, convertedEndTime);
		long gap = duration.toMinutes();
		System.out.println("StartTime-EndTime= " + gap);
		if (gap >= 15) {
			List<DetailsOfMeeting> overLapList = meetingScheduleREPO.overLap(detailsOfMeeting.getCreatedBy(),
					detailsOfMeeting.getMeetingWith(), detailsOfMeeting.getMeetingDate(),
					detailsOfMeeting.getStartTime(), detailsOfMeeting.getEndTime());
			if (overLapList.isEmpty()) {
				detailsOfMeeting = meetingScheduleREPO.save(detailsOfMeeting);
				return detailsOfMeeting;
			} else {
				System.out.println(detailsOfMeeting);
				return detailsOfMeeting = null;
			}
		} else {
			throw new CustomAppException("MEETING TIME MUST BE ATLEAST 15 MIN",
					"Meeting time must be atleast 15 min", HttpStatus.BAD_REQUEST);
		}
	}

	public List<DetailsOfMeeting> getDetailsByField(ScheduleMeetingDTO scheduleMeetingDTO) {
		if (scheduleMeetingDTO.getId() > 0) {
			List<DetailsOfMeeting> detailsOfMeetings = meetingScheduleREPO.findByIds(scheduleMeetingDTO.getId());
			return detailsOfMeetings;
		} else if (scheduleMeetingDTO.getCreatedBy() != null) {
			List<DetailsOfMeeting> detailsOfMeetings = meetingScheduleREPO
					.findByCreatedBy(scheduleMeetingDTO.getCreatedBy());
			return detailsOfMeetings;
		} else if (scheduleMeetingDTO.getStartDate() != null && scheduleMeetingDTO.getEndDate() != null) {
			List<DetailsOfMeeting> detailsOfMeetings = meetingScheduleREPO
					.findByDates(scheduleMeetingDTO.getStartDate(), scheduleMeetingDTO.getEndDate());
			return detailsOfMeetings;
		}
		return null;
	}

	public DetailsOfMeeting updateAt(int id, ScheduleMeetingDTO scheduleMeetingDTO) {
		DetailsOfMeeting updateDetailsOfMeeting = meetingScheduleREPO.findById(scheduleMeetingDTO.getId())
				.orElseThrow(() -> new CustomAppException("DETAILS NOT FOUND WITH ID","details not Found with Id",HttpStatus.BAD_REQUEST));
		updateDetailsOfMeeting.setCreatedBy(scheduleMeetingDTO.getCreatedBy());
		meetingScheduleREPO.save(updateDetailsOfMeeting);
		return updateDetailsOfMeeting;
	}

	public DetailsOfMeeting deleteDetailsAt(int id) {
		meetingScheduleREPO.deleteById(id);
		return detailsOfMeeting;
	}

	public List<String> getDateFromREPO(String currentDate) {
		List<String> dateFromREPO = meetingScheduleREPO.findForTodaysDate(currentDate);
		return dateFromREPO;
	}

	public List<DetailsOfMeeting> getTodaysMeetings(String todaysDate) {
		List<DetailsOfMeeting> dateFromREPO = meetingScheduleREPO.findTodaysMeetings(todaysDate);
		return dateFromREPO;
	}

	public List<DetailsOfMeeting> overLapping(DetailsOfMeeting detailsOfMeeting) {
		List<DetailsOfMeeting> overLapList = meetingScheduleREPO.overLap(detailsOfMeeting.getCreatedBy(),
				detailsOfMeeting.getMeetingWith(), detailsOfMeeting.getMeetingDate(), detailsOfMeeting.getStartTime(),
				detailsOfMeeting.getEndTime());
		return overLapList;
	}
}
