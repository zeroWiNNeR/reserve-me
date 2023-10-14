package com.reserveme.backend.model.entity.auth;

import com.reserveme.backend.model.entity.establishment.Establishment;
import jakarta.persistence.*;

@Entity
@Table(name = "rm_user_owner")
public class Owner extends User {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estblsh_id")
	private Establishment establishment;


	public Establishment getEstablishment() {
		return establishment;
	}

	public void setEstablishment(Establishment establishment) {
		this.establishment = establishment;
	}

}
