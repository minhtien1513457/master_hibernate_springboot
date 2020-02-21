package com.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NamedQueries({
	@NamedQuery(name="get_all_course",
				query="from Course c"),
	
	@NamedQuery(name="find_course_by_id",
	query="from Course c where c.id=:id")
})
@NamedNativeQueries({
	@NamedNativeQuery(name="find_course_by_id_native",
					  query="select * from course c where c.id=:id")
})
@Data
@NoArgsConstructor
public class Course implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	public Course(String name) {
		super();
		this.name = name;
	}

	@Column(nullable= false)
	private String name;
	
	@CreationTimestamp
	private LocalDateTime create_time;
	
	@UpdateTimestamp
	private LocalDateTime update_time;
}
