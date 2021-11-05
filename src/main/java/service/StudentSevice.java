package service;


import dto.StudentDTO;
import exceptions.BusinessException;
import mapper.StudentMapper;
import model.Student;
import repository.StudentDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class StudentSevice {

    private StudentDAO studentDAO = new StudentDAO();
    private StudentMapper studentMapper = new StudentMapper();

    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> studentDTOS;
        List<Student> students = studentDAO.getALLStudent();
        studentDTOS = studentMapper.toDTO(students);
        if (!students.isEmpty()) {
            return studentDTOS;
        } else {
            return null;
        }
    }

    public List<StudentDTO> getStudentWithPagination(int firstResult, int pageSize) {
        List<StudentDTO> studentDTOS;
        List<Student> students = studentDAO.getListStudentWithPagingnation(firstResult, pageSize);
        studentDTOS = studentMapper.toDTO(students);
        if (!students.isEmpty()) {
            return studentDTOS;
        } else {
            return null;
        }
    }

    private boolean isValid(String text) {
        if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

    public boolean validateStudent(Student student) {
        boolean check;
        try {
            if (student.getClassName().isEmpty() || student.getClassName().trim() == "") {
                check = false;
                throw new BusinessException ("Tên lớp không được để trống hoặc rỗng");
            }
            if (Double.valueOf(student.getAverageMark()) == 0) {
                check = false;
                throw new BusinessException("Điểm trung bình không được bằng 0");
            }
            if (student.getFullName().isEmpty() || student.getFullName().trim() == "") {
                check = false;
                throw new BusinessException("Họ tên không được để rỗng hoặc trống");
            }
            if (student.getFullName().length() > 50) {
                check = false;
                throw new BusinessException("Họ tên không dài quá 50 ký tự");
            }
            if (student.getBirthDay().toString().isEmpty() || student.getBirthDay().toString().trim() == "") {
                check = false;
                throw new BusinessException("Ngày sinh không được để trống hoặc rỗng");
            }
            if (!isValid(student.getBirthDay().toString())) {
                check = false;
                throw new BusinessException("Ngày sinh là định dạng mmmm-MMM-dd");
            }
            if (student.getGender().isEmpty() || student.getGender().trim() == "") {
                check = false;
                throw new BusinessException("Giới tính không được để rỗng");
            }
            if (student.getHometown().isEmpty() || student.getHometown().trim() == "") {
                check = false;
                throw new BusinessException("Nơi ở không được để rỗng hoặc trống");
            }

            if (student.getMajor() == null || student.getMajor().isEmpty()) {
                check = false;
                throw new BusinessException("Tên Khoa không được để trống");
            } else {
                check = true;
            }
        } catch (BusinessException e) {
            check = false;
        }
        return check;

    }

    public boolean createStudent(Student student)  {
            return studentDAO.createStudent(student);
    }

    public Student getStudentById(int p_studentId) {
        return studentDAO.getById(p_studentId);
    }

    public boolean deleteStudent(int p_studentId) {
        return studentDAO.deleteStudent(p_studentId);
    }

    public boolean updateStudentById(Student oldStudent, Student newStudent) {
        try {
            oldStudent.setAverageMark(newStudent.getAverageMark());
            oldStudent.setBirthDay(newStudent.getBirthDay());
            oldStudent.setClassName(newStudent.getClassName());
            oldStudent.setGender(newStudent.getGender());
            oldStudent.setHometown(newStudent.getHometown());
            oldStudent.setFullName(newStudent.getFullName());
            oldStudent.setMajor(newStudent.getFullName());
            studentDAO.update(oldStudent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
