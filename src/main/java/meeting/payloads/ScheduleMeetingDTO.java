package meeting.payloads;

import java.sql.Date;
import java.sql.Time;

public class ScheduleMeetingDTO {
	
private int id;
	
	private String createdBy;
	
	private String meetingWith;
	
	private Date meetingDate;
	
	private Time starTime;
	
	private Time endTime;
	
	private Date startDate;
	
	private Date endDate;
	
	private String curreDate;
	
	
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
	public Date getMeetingDate() {
		return meetingDate;
	}
	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}
	public Time getStarTime() {
		return starTime;
	}
	public void setStarTime(Time starTime) {
		this.starTime = starTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	public String getCurreDate() {
		return curreDate;
	}
	public void setCurreDate(String curreDate) {
		this.curreDate = curreDate;
	}
	
	
	@Override
	public String toString() {
		return "ScheduleMeetingDTO [id=" + id + ", createdBy=" + createdBy + ", meetingWith=" + meetingWith
				+ ", meetingDate=" + meetingDate + ", starTime=" + starTime + ", endTime=" + endTime + ", startDate="
				+ startDate + ", endDate=" + endDate + ", curreDate=" + curreDate + "]";
	}

}
