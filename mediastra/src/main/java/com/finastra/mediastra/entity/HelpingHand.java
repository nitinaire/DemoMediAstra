package com.finastra.mediastra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "helping_hand")
public class HelpingHand {
	private long id;
	private String name;
	private String reason;

	public HelpingHand() {

	}

	public HelpingHand(String name, String reason) {
		super();
		this.name = name;
		this.reason = reason;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "r", nullable = false)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "HelpingHand [id=" + id + ", name=" + name + ", reason=" + reason + "]";
	}

}
