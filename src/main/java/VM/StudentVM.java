package VM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class StudentVM {

    private String fullName;

    private Date birthDay;

    private String className;

    private String Major;

    private  String hometown;

    private  String gender;

    private  double averageMark ;
}
