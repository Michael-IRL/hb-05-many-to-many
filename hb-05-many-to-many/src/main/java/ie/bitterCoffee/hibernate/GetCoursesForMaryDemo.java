package ie.bitterCoffee.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import ie.bitterCoffee.hibernate.entity.Course;
import ie.bitterCoffee.hibernate.entity.Instructor;
import ie.bitterCoffee.hibernate.entity.InstructorDetail;
import ie.bitterCoffee.hibernate.entity.Review;
import ie.bitterCoffee.hibernate.entity.Student;

public class GetCoursesForMaryDemo
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
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		//Create session
		Session session = factory.getCurrentSession();
		
		try
		{						
			//start a transaction
			session.beginTransaction();
			
			//get mary from databasae
			int studentId = 2;
			Student student = session.get(Student.class, studentId);
			
			System.out.println("\nLoaded student: "+student);
			System.out.println("Coures: "+student.getCourses());
			
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
