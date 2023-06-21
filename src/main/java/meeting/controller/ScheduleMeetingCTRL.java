package meeting.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import meeting.entity.DetailsOfMeeting;
import meeting.exception.CustomAppException;
import meeting.exception.CustomAppExceptionHandler;
import meeting.exception.Response;
import meeting.payloads.ScheduleMeetingDTO;
import meeting.repo.MeetingScheduleREPO;
import meeting.services.ScheduleMeetingSERV;

@RestController
@RequestMapping("/meeting")
@CrossOrigin
public class ScheduleMeetingCTRL {

	SimpleDateFormat formateDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	ScheduleMeetingSERV scheduleMeetingSERV;

	@Autowired
	MeetingScheduleREPO meetingScheduleREPO;

	@GetMapping("/get-meeting-details")
	public ResponseEntity<?> getDetailsOfMeetings() {
		try {

			return new ResponseEntity<>(new Response<>("DETAILS_FETCHED_SUCCESSFULLY", "DETAILS_FETCHED_SUCCESSFULLY",
					scheduleMeetingSERV.getMeetingDetails()), HttpStatus.OK);
		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE FETCHIND DATA", null), HttpStatus.OK);
		}

	}

	@PostMapping("/save-details")
	public ResponseEntity<?> saveDetails(@RequestBody DetailsOfMeeting detailsOfMeeting) {

		try {
			detailsOfMeeting = scheduleMeetingSERV.saveDetailsOfMeeting(detailsOfMeeting);
			if (detailsOfMeeting != null) {
				return new ResponseEntity<>(
						new Response<>("DETAILS_SAVED_SUCCESSFULLY", "DETAILS_SAVED_SUCCESSFULLY", detailsOfMeeting),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<>(
						new Response<>("Meeting Timings are Over Lapping for the Selected Person",
								"Meeting Timings are Over Lapping for the Selected Person", detailsOfMeeting),
						HttpStatus.OK);
			}

		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE SAVING DATA", null), HttpStatus.OK);
		}

	}

	@PostMapping("/get-detailsByField")
	public ResponseEntity<?> getDetailsByField(@RequestBody ScheduleMeetingDTO scheduleMeetingDTO) {
		try {
			return new ResponseEntity<>(new Response<>("DETAILS_FETCHED_SUCCESSFULLY", "DETAILS_FETCHED_SUCCESSFULLY",
					scheduleMeetingSERV.getDetailsByField(scheduleMeetingDTO)), HttpStatus.OK);
		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE FETCHIND DATA", null), HttpStatus.OK);
		}
	}

	@PutMapping("/update-details")
	public ResponseEntity<?> updateDetails(@RequestBody ScheduleMeetingDTO scheduleMeetingDTO) {

		try {
			return new ResponseEntity<>(
					new Response<>("DATA_UPDATED_SUCCESSFULLY", "DATA_UPDATED_SUCCESSFULLY",
							scheduleMeetingSERV.updateAt(scheduleMeetingDTO.getId(), scheduleMeetingDTO)),
					HttpStatus.OK);
		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE UPDATING DATA", null), HttpStatus.OK);
		}

	}

	@DeleteMapping("/delete-details/{id}")
	public ResponseEntity<?> deleteDetails(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(new Response<>("DATA_DELETED_SUCCESSFULLY", "DATA_DELETED_SUCCESSFULLY",
					scheduleMeetingSERV.deleteDetailsAt(id)), HttpStatus.OK);
		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE DELETING DATA", null), HttpStatus.OK);
		}

	}

	@GetMapping("todays-date-meetings")
	public ResponseEntity<?> dates() {
		Date date = new Date();
		String currentDate = formateDateFormat.format(date);		
		try {
			return new ResponseEntity<>(new Response<>("LIST_OF_TODAYS_MEETING", "LIST_OF_TODAYS_MEETING",
					scheduleMeetingSERV.getTodaysMeetings(currentDate)), HttpStatus.OK);
		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE FETCHING DATA", null), HttpStatus.OK);
		}

	}

	@PostMapping("/over-lap-details")
	public ResponseEntity<?> overLapDetails(@RequestBody DetailsOfMeeting detailsOfMeeting) {
		
		try {
			return new ResponseEntity<>(new Response<>("OVERLAP_MEETINGS", "OVERLAP_MEETINGS",
					scheduleMeetingSERV.overLapping(detailsOfMeeting)), HttpStatus.OK);
		} catch (CustomAppException e) {
			return new CustomAppExceptionHandler().appExceptionResponse(e);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new Respons<>("ERROR OCCURED WHILE FETCHING DATA", null), HttpStatus.OK);
		}
	}

}
