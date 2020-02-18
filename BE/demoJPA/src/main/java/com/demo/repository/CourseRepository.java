package com.demo.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	@Autowired
	EntityManager em;
	
	public Course findById(long id) {
		return em.find(Course.class, id);
	}
	
	public void deleteById(long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public Course save(Course course) {
		if(course.getId() == 0L) {
			em.persist(course);
		} else {
			em.merge(course);
		}
		return course;
	}
	
	
	
	
}
