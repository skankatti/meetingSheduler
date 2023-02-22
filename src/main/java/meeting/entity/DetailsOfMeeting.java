package meeting.entity;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Service
@Entity
@Table(name = "details_of_meeting")
public class DetailsOfMeeting {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String createdBy;

	private String meetingWith;

	private String meetingDate;

	private String startTime;

	private String endTime;

	private String description;
	
	private String createdByEmail;

	private String meetingWithEmail;

	private boolean mailSentStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getMeetingWith() {
		return meetingWith;
	}

	public void setMeetingWith(String meetingWith) {
		this.meetingWith = meetingWith;
	}

	public String getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(String meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMeetingWithEmail() {
		return meetingWithEmail;
	}

	public void setMeetingWithEmail(String meetingWithEmail) {
		this.meetingWithEmail = meetingWithEmail;
	}

	public Boolean getMailSentStatus() {
		return mailSentStatus;
	}

	public void setMailSentStatus(Boolean mailSentStatus) {
		this.mailSentStatus = mailSentStatus;
	}

	public String getCreatedByEmail() {
		return createdByEmail;
	}

	public void setCreatedByEmail(String createdByEmail) {
		this.createdByEmail = createdByEmail;
	}

	@Override
	public String toString() {
		return "DetailsOfMeeting [id=" + id + ", createdBy=" + createdBy + ", meetingWith=" + meetingWith
				+ ", meetingDate=" + meetingDate + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", description=" + description + ", createdByEmail=" + createdByEmail + ", meetingWithEmail="
				+ meetingWithEmail + ", mailSentStatus=" + mailSentStatus + "]";
	}



}
