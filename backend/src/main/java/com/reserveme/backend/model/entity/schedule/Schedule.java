package com.reserveme.backend.model.entity.schedule;

import com.reserveme.backend.model.entity.DomainObject;
import com.reserveme.backend.model.entity.auth.Client;
import jakarta.persistence.*;

@Entity
@Table(name = "rm_schedule")
public class Schedule extends DomainObject {

	@Column(name = "name")
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Client user;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Client getUser() {
		return user;
	}

	public void setUser(Client user) {
		this.user = user;
	}
}
