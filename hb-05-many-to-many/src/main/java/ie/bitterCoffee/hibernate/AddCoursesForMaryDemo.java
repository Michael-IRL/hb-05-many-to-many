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

public class AddCoursesForMaryDemo
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
			
			
			
			// get student by name
			String name = "%"+"mary";
			Query<Student> query = session.createQuery("from Student s where s.firstName like :name",Student.class);
			query.setParameter("name", name);			
			Student student = query.getSingleResult();
			
			System.out.println("\nthe student we got was: " + student+"\nWith: "+name);
			System.out.println(student.getFirstName()+"'s courses are: "+student.getCourses());
			
			//create courses
			Course course1 = new Course("Games master");
			Course course2 = new Course("Guitar for stage");
			Course course3 = new Course("Java codeing");
			
			//add student to courses
			course1.addStudent(student);
			course2.addStudent(student);
			course3.addStudent(student);
			
			//save the courses
			System.out.println("\nSaving the courses\n");
			session.save(course1);
			session.save(course2);
			session.save(course3);
			System.out.println("\nCourses saved\n");
			
			
			
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
