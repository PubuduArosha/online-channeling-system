package model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Malidi
 *
 */

@XmlRootElement
public class Patient {
	
	
	private int patientID;
	
	private String NIC;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String gender;
	
	private String address;
	
	private String password;
	
	private String city;
	
	private String contact;

	public int getPatientID() {
		return patientID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", NIC=" + NIC + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", gender=" + gender + ", address=" + address + ", password="
				+ password + ", city=" + city + ", contact=" + contact + "]";
	}

	

	
	
	
	
	

	
	

}
