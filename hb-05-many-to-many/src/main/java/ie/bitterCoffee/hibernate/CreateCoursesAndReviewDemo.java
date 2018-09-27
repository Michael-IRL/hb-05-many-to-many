package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ie.bitterCoffee.hibernate.entity.Course;
import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;
import ie.bitterCoffee.hibernate.entity.Review;

public class CreateCoursesAndReviewDemo
{

	public static void main(String[] args)
	{
		//Create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.addAnnotatedClass(Review.class)
									.buildSessionFactory();
		
		//Create session
		Session session = factory.getCurrentSession();
		
		try
		{						
			//start a transaction
			session.beginTransaction();
			
			//create a course
			Course course = new Course("Pinball wizzard");			
			
			
			course.add(new Review("Best course ever"));
			course.add(new Review("Amazing pinball course"));
			course.add(new Review("I now own a pinball table because of this course"));
			
			//save the course and leverage the cascade all
			System.out.println("Saving Course: "+course);
			System.out.println("Course reviews "+course.getReviews());
			session.save(course);
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			session.close();
			factory.close();
		}			
	}

}
