package controller;

import VM.StudentVM;
import dto.StudentDTO;
import exceptions.BusinessException;
import model.Student;
import org.apache.log4j.Logger;
import repository.StudentDAO;
import service.StudentSevice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Path("/students")
public class StudentController {
    private StudentSevice studentSevice = new StudentSevice();
    Logger logger = Logger.getLogger(StudentController.class);
    private StudentDAO studentDAO = new StudentDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentDTO> getListStudent() {
        List<StudentDTO> studentDTOS = studentSevice.getAllStudent();
        if (studentDTOS.isEmpty()) {
            logger.error("Ket noi that bai");
            return null;
        } else {
            logger.error("Ket noi thanh cong");
            return studentDTOS;
        }
    }

    @GET
    @Path("/{first-result}/{page-size}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentDTO> getListStudentWithPagination(@PathParam("first-result") int firstResult, @PathParam("page-size") int pageSize) {
        List<StudentDTO> studentDTOS = studentSevice.getStudentWithPagination(firstResult, pageSize);
        if (studentDTOS.isEmpty()) {
            logger.error("Ket noi that bai");
            return null;
        } else {
            logger.error("Ket noi thanh cong");
            return studentDTOS;
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String createStudent(Student student) {
        boolean checkStudent = studentSevice.validateStudent(student);
        if (!checkStudent) {
            return "Thông tin sinh viên không hợp lệ";
        }
        return studentSevice.createStudent(student) ? "Thêm mới Sinh viên thành công" : "Thêm mới sinh viên thất bại";

    }


    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteStudentById(@PathParam("id") int p_studentId) {
        Student oldStudent = studentSevice.getStudentById(p_studentId);
        if (oldStudent == null) {
            return "id không hợp lệ";
        }
        return studentSevice.deleteStudent(p_studentId) ? "xóa sinh viên thành công" : "xóa sinh viên thất bại";
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateStudentById(@PathParam("id") int p_studentId, Student newStudent) {
        Student oldStudent = studentSevice.getStudentById(p_studentId);
        if (oldStudent == null) {
            return "id không hợp lệ";
        }
        boolean checkStudent = studentSevice.validateStudent(newStudent);
        if (!checkStudent) {
            return "Thông tin sinh viên không hợp lệ";
        }
        return studentSevice.updateStudentById(oldStudent, newStudent) ? "update sinh viên thành công" : "update sinh viên thất bại";
    }
    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Student> findStudent(StudentVM studentVM){
         return studentDAO.findStudent(studentVM);
    }

}