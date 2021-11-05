package model;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Student {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_day", nullable = false)
    private Date birthDay;

    @Column(name = "class_name", nullable = false)
    private String className;

    @Column(name = "major", nullable = false)
    private String Major;

    @Column(name = "home_town", nullable = false)
    private  String hometown;

    @Column(name = "gender", nullable = false)
    private  String gender;

    @Column(name = "average_mark", nullable = false)
    private  double averageMark ;


}
