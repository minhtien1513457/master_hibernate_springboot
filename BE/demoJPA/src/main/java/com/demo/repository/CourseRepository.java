package com.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.entity.Course;

@Repository
@Transactional
public class CourseRepository {
	@Autowired
	EntityManager em;
	
	Logger logger = LoggerFactory.getLogger(CourseRepository.class);
	
	
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
	
	public void play() {
		Course course1 = new Course("hello1");
		em.persist(course1);
		em.flush();
		
		course1.setName("update1");
		em.flush();

		Course course2 = new Course("hello2");
		em.persist(course2);
		em.flush();

		em.detach(course2);
		course2.setName("update2");
		em.flush();
		
		TypedQuery<Course> query = em.createNamedQuery("get_all_course",Course.class);
		List<Course> result = query.getResultList();
		logger.info("all course -> {}",result);
		
		Query queryNative = em.createNativeQuery("select * from Course c where c.id=:id", Course.class);
		queryNative.setParameter("id", 25);
		List<Course> result2 = queryNative.getResultList();
		logger.info("query native -> {}",result2);

		
	}
	
	
	
	
}
