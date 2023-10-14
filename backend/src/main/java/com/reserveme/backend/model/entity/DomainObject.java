package com.reserveme.backend.model.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class DomainObject {

	public static final String SEQUENCE_NAME = "RM_SEQ";

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id == null ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		if (obj.getClass() == getClass()) {
			DomainObject other = (DomainObject) obj;
			return !(id == null || other.id == null) && id.equals(other.id);
		}

		return false;
	}

}
