

package hospital.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

/**
 *
 * @author MacMuffin
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="room_reservation")
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class RoomReservation {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToOne(cascade={
			CascadeType.DETACH, 
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH,
			}
	)
	@JoinColumn(name="hospital_room_id")
	private HospitalRoom hospitalRoom;
	
	@Column(name="room_code")
	private String roomCode;
	
	@Column(name="reserved_by_user_id")
	private String reservedByUserId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="reserved_date")
	private LocalDate reservedDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
//	@DateTimeFormat(pattern = "HH:mm:ss")
	@Column(name="reserved_time")
	private LocalTime reservedTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="reserved_end_date")
	private LocalDate reservedEndDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
//	@DateTimeFormat(pattern = "HH:mm:ss")
	@Column(name="reserved_end_time")
	private LocalTime reservedEndTime;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="reservation_status")
	private int reservationStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getReservedByUserId() {
		return reservedByUserId;
	}

	public void setReservedByUserId(String reservedByUserId) {
		this.reservedByUserId = reservedByUserId;
	}
	
	public LocalDate getReservedDate() {
		return reservedDate;
	}

	public void setReservedDate(LocalDate reservedDate) {
		this.reservedDate = reservedDate;
	}
	
	public LocalTime getReservedTime() {
		return reservedTime;
	}

	public void setReservedTime(LocalTime reservedTime) {
		this.reservedTime = reservedTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public HospitalRoom getHospitalRoom() {
		return hospitalRoom;
	}

	public void setHospitalRoom(HospitalRoom hospitalRoom) {
		this.hospitalRoom = hospitalRoom;
	}

	public int getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(int reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public LocalDate getReservedEndDate() {
		return reservedEndDate;
	}

	public void setReservedEndDate(LocalDate reservedEndDate) {
		this.reservedEndDate = reservedEndDate;
	}

	public LocalTime getReservedEndTime() {
		return reservedEndTime;
	}

	public void setReservedEndTime(LocalTime reservedEndTime) {
		this.reservedEndTime = reservedEndTime;
	}
	
	
}
