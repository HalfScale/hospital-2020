

package hospital.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
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
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
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
	@JsonManagedReference
	@JoinColumn(name="hospital_room_id")
	private HospitalRoom hospitalRoom;
	
	@Column(name="room_code")
	private String roomCode;
	
	@Column(name="reserved_by_user_id")
	private String reservedByUserId;
	
	@Column(name="has_associated_appointment")
	private boolean hasAssociatedAppointment;
	
	@Column(name="associated_appointment_id")
	private Integer associatedAppointmentId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name="reserved_date")
	private LocalDate reservedDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@Column(name="reserved_time")
	private LocalTime reservedTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@Column(name="reserved_end_date")
	private LocalDate reservedEndDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
	@JsonSerialize(using = LocalTimeSerializer.class)
	@JsonDeserialize(using = LocalTimeDeserializer.class)
	@Column(name="reserved_end_time")
	private LocalTime reservedEndTime;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="reservation_status")
	private int reservationStatus;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name="created")
	private LocalDateTime created;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name="modified")
	private LocalDateTime modified;
	
	@Column(name="deleted")
	private boolean deleted;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name="deleted_date")
	private LocalDateTime deletedDate;

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

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getModified() {
		return modified;
	}

	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public LocalDateTime getDeletedDate() {
		return deletedDate;
	}

	public void setDeletedDate(LocalDateTime deletedDate) {
		this.deletedDate = deletedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public boolean isHasAssociatedAppointment() {
		return hasAssociatedAppointment;
	}

	public void setHasAssociatedAppointment(boolean hasAssociatedAppointment) {
		this.hasAssociatedAppointment = hasAssociatedAppointment;
	}

	public Integer getAssociatedAppointmentId() {
		return associatedAppointmentId;
	}

	public void setAssociatedAppointmentId(Integer associatedAppointmentId) {
		this.associatedAppointmentId = associatedAppointmentId;
	}
}
