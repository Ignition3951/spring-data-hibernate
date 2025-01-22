package com.utk.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.utk.interceptor.Auditable;

@Entity
public class AuditLogRecord {

	@Id
	@GeneratedValue(generator = "ID_GENERATOR")
	private Long id;

	@NotNull
	private String message;

	@NotNull
	private Long entityId;

	@NotNull
	private Class<? extends Auditable> entityClass;

	@NotNull
	private Long userId;

	@NotNull
	private LocalDateTime createdOn = LocalDateTime.now();

	public AuditLogRecord() {
	}

	public AuditLogRecord(@NotNull String message, Auditable entityInstance,
			@NotNull Long userId) {
		this.message = message;
		this.entityId = entityInstance.getId();
		this.entityClass = entityInstance.getClass();
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getEntityId() {
		return entityId;
	}

	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}

	public Class<? extends Auditable> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<? extends Auditable> entityClass) {
		this.entityClass = entityClass;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public Long getId() {
		return id;
	}

}
