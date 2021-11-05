package repository;

import VM.StudentVM;
import model.Student;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class StudentDAO {
    Logger logger = Logger.getLogger(StudentDAO.class);

    public List<Student> getALLStudent() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Student> query = session.createQuery("from Student ");
            List<Student> students = query.list();
            return students;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> getListStudentWithPagingnation(int firstResult, int pageSize) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query<Student> query = session.createQuery("from Student ");
            query.setFirstResult(firstResult);
            query.setMaxResults(pageSize);
            List<Student> students = query.list();
            return students;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean createStudent(Student student) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            logger.error(e);
        }
        return false;
    }

    public boolean deleteStudent(long p_studentId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Student student = session.load(Student.class, p_studentId);
            session.delete(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            logger.error(e);
        }
        return false;
    }

    public Student getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Query<Student> query = session.createQuery("FROM Student  WHERE id = :p_student_id ");
            query.setParameter("p_student_id", id);
            return !query.list().isEmpty() ? query.getSingleResult() : null;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Student student) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> findStudent(StudentVM studentVM) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            StringBuilder sql = new StringBuilder();
            HashMap<String, Object> hmapParams = new HashMap<>();
            sql.append(" FROM Student  WHERE  ");
            if (studentVM.getFullName() != "" || studentVM.getFullName() != null) {
                sql.append("fullName like concat('%', :p_student_name, '%') ");
                hmapParams.put("p_student_name", studentVM.getFullName());
            }
            if (studentVM.getHometown() != "" || studentVM.getHometown() != null) {
                sql.append("or hometown like :p_home_town ");
                hmapParams.put("p_home_town", studentVM.getHometown());
            }
            Query<Student> query = session.createQuery(String.valueOf(sql));
            hmapParams.forEach((k, v) -> query.setParameter(k, v));

            return !query.list().isEmpty() ? query.getResultList() : null;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

}