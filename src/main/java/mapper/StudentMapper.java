package mapper;

import dto.StudentDTO;
import model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentMapper {
    StudentDTO studentDTO = new StudentDTO();
    List<StudentDTO> studentDTOS = new ArrayList<>();

    public List<StudentDTO> toDTO(List<Student> students){

       for( int i =0; i< students.size(); i++){
           StudentDTO newStudentDTO = new StudentDTO();
           newStudentDTO.setFullName(students.get(i).getFullName());
           newStudentDTO.setId(students.get(i).getId());
           newStudentDTO.setBirthDay(students.get(i).getBirthDay());
           newStudentDTO.setGender(students.get(i).getGender());
           newStudentDTO.setHometown(students.get(i).getHometown());
           newStudentDTO.setMajor(students.get(i).getMajor());
           newStudentDTO.setClassName(students.get(i).getClassName());
           newStudentDTO.setAverageMark(students.get(i).getAverageMark());
           studentDTOS.add(newStudentDTO);
        }
       return studentDTOS;
    }
}
