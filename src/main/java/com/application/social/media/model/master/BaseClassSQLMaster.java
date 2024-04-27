package com.application.social.media.model.master;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
@MappedSuperclass
public abstract class BaseClassSQLMaster implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreationTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    protected Date createdAt;

    @UpdateTimestamp
    @ColumnDefault("CURRENT_TIMESTAMP")
    protected Date updatedAt;
    
    @ColumnDefault("1")
    @Column(nullable = true )
    protected Long createdBy;

    
    @Column(nullable = true)
    protected Long updatedBy;
    
    @NotNull
	@Column(columnDefinition = "boolean default true")
	protected Boolean isActive;

}
