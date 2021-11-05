package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class StudentDTO {

    private Long id;

    private String fullName;

    private Date birthDay;

    private String className;

    private String Major;

    private  String hometown;

    private  String gender;

    private  double averageMark ;


}
