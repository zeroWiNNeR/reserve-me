package com.reserveme.backend.model.entity.establishment;

import com.reserveme.backend.model.entity.DomainObject;
import com.reserveme.backend.model.entity.auth.Owner;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rm_estblsh")
public class Establishment extends DomainObject {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "establishment")
	private List<Owner> owners;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Owner> getOwners() {
		return owners;
	}

	public void setOwners(List<Owner> owners) {
		this.owners = owners;
	}

}
