

package hospital.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author MacMuffin
 */
@Entity
@Table(name="appointment")
public class Appointment {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="patient_id")
	private Integer patientId;
	
	@Column(name="doctor_id")
	private Integer doctorId;
	
	@Column(name="appointment_status")
	private String appointmentStatus;
	
	@OneToMany(mappedBy="appointment", cascade=CascadeType.ALL)
	private List<AppointmentHistory> appointmentHistories;
	
	@OneToOne(mappedBy="appointment", cascade=CascadeType.ALL)
	private AppointmentDetail appointmentDetail;
	
	@OneToOne(mappedBy="appointment", cascade=CascadeType.ALL)
	private AppointmentDetailHistory appointmentDetailHistories;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	
}
