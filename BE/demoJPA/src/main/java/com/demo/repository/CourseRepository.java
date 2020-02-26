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
import com.demo.entity.Student;

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
		
//		Course course1 = new Course("hello1");
//		em.persist(course1);
//		em.flush();
		//course1.setName("update");
		
//		course1.setName("update1");
//		em.flush();
//
//		Course course2 = new Course("hello2");
//		em.persist(course2);
//		em.flush();
//
//		em.detach(course2);
//		course2.setName("update2");
//		em.flush();
//		
//		TypedQuery<Course> query = em.createNamedQuery("get_all_course",Course.class);
//		List<Course> result = query.getResultList();
//		logger.info("all course -> {}",result);
//		
//		Query queryParam = em.createNamedQuery("find_course_by_id", Course.class);
//		queryParam.setParameter("id", 17L);
//		List<Course> result3 = queryParam.getResultList();
//		logger.info("query find -> {}",result3);
		
//		Query queryNative = em.createNativeQuery("find_course_by_id_native", Course.class);
//		queryNative.setParameter("id", 17);
//		List<Course> result2 = queryNative.getResultList();
//		logger.info("query native find -> {}",result2);
		
		TypedQuery<Course> query_get_courses_without_student = em.createQuery("select c from Course c where c.students is empty",Course.class);
		List<Course> result = query_get_courses_without_student.getResultList();
		
		TypedQuery<Course> query_get_courses_atleast_2_student = em.createQuery("select c from Course c where size(c.students) >=2",Course.class);
		List<Course> result1 = query_get_courses_atleast_2_student.getResultList();
		
		TypedQuery<Course> query_get_courses_order_by_size_student = em.createQuery("select c from Course c where order by size(c.students) desc",Course.class);
		List<Course> result2 = query_get_courses_order_by_size_student.getResultList();

		TypedQuery<Student> query_get_student_have_passport_like = em.createQuery("select s from Student s where s.passport.number like '%123%'",Student.class);
		List<Student> result3 = query_get_student_have_passport_like.getResultList();
		
		Query query_left_join = em.createQuery("select c, s from Course c LEFT JOIN c.students s",Student.class);
		List<Object[]> result4 = query_left_join.getResultList();
		
		Query query_cross_join = em.createQuery("select c, s from Course c, Student s",Student.class);
		List<Object[]> result5 = query_cross_join.getResultList();
		
	}
	
	
	
	
}
