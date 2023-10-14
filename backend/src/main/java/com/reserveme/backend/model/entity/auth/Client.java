package com.reserveme.backend.model.entity.auth;

import com.reserveme.backend.model.entity.schedule.Schedule;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rm_user_client")
public class Client extends User {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	List<Schedule> scheduleList;

	public List<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
}
