package com.hatio.Entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "todo")
public class TodoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String description;

	private String status;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date createDate;
	
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date updateDate;
	
	@ManyToOne
	private ProjectEntity projectEntity;
	
}
