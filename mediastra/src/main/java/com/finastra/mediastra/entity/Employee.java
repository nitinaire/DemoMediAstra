package com.finastra.mediastra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	private long id;
	private String firstName;
	private String lastName;
	private String emailId;
	private String employeeID;
	private String bloodGroup;
	private String isIntake;
	private String city;

	@Column(name = "isIntake", nullable = false)
	public String getIsIntake() {
		return isIntake;
	}

	public void setIsIntake(String isIntake) {
		this.isIntake = isIntake;
	}

	private String vacAddress;

	public Employee() {

	}

	public Employee(String firstName, String lastName, String emailId, String employeeID, String bloodGroup,
			String isIntake, String vacAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.employeeID = employeeID;
		this.bloodGroup = bloodGroup;
		this.isIntake = isIntake;
		this.vacAddress = vacAddress;
		this.city = city;
	}

	@Column(name = "blood_group", nullable = false)
	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Column(name = "vac_addr", nullable = true)
	public String getVacAddress() {
		return vacAddress;
	}

	public void setVacAddress(String vacAddress) {
		this.vacAddress = vacAddress;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "first_name", nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name", nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "emp_id", nullable = false)
	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	@Column(name = "city")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ "]";
	}

}