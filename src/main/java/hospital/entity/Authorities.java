package hospital.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;



@Entity
@Table(name = "authorities")
public class Authorities implements GrantedAuthority{

	@Id
	@Column(name = "authority")
	private String authority;

	@ManyToOne
	@JoinColumn(name = "users_id")
	private User user;
	
	public Authorities() {}
	
	public Authorities(String authority, User user) {
		this.authority = authority;
		this.user = user;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
