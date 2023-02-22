package meeting.repo;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import meeting.entity.DetailsOfMeeting ;

@Repository
public interface MeetingScheduleREPO extends JpaRepository<DetailsOfMeeting, Integer>{
	
	
	//Query to find details of meeting with createdBy field
	@Query(value="SELECT * FROM details_of_meeting model where model.created_by LIKE %:createdBy%", nativeQuery = true)
	public List<DetailsOfMeeting> findByCreatedBy(String createdBy);

	
	//Query to find overlap meetings
	@Query(value="SELECT * from details_of_meeting model where model.created_by=:createdBy "
			+ "or model.meeting_with=:meetingWith and "
			+ "model.meeting_date=:meetingDate and model.end_time between :startTime and :endTime",nativeQuery = true)
	public List<DetailsOfMeeting> overLap(String createdBy, String meetingWith, String meetingDate, String startTime, String endTime);
	
	//Query to find with id
	@Query(value="SELECT * FROM details_of_meeting model where model.id=:id", nativeQuery = true)
	public List<DetailsOfMeeting> findByIds(int id);
	

	//Query to find meeting details which are in between start and end time
	@Query(value="SELECT * from details_of_meeting where meeting_date BETWEEN :startDate AND :endDate", nativeQuery = true)
	public List<DetailsOfMeeting> findByDates(Date startDate, Date endDate);
	
	
	//Query to find meeting details by date
	@Query(value="SELECT meeting_date from details_of_meeting where meeting_date=:currentDate",nativeQuery = true)
	public List<String> findForTodaysDate(String currentDate);
	

	//Query to find CurrentDates meeting details
	@Query(value="SELECT * from details_of_meeting model where model.meeting_date=:currentDate",nativeQuery = true)
	public List<DetailsOfMeeting> findTodaysMeetings(String currentDate);
	
	// Query to Update mailsent status
	@Modifying
	@Transactional
	@Query(value = "UPDATE details_of_meeting set mail_sent_status = true "
			+ "where created_by=:createdBy and meeting_with=:meetingWith "
			+ "and start_time=:startTime and end_time=:endTime",nativeQuery = true)
	public void updateMailSentStatus(String createdBy, String meetingWith, String startTime, String endTime);
}
