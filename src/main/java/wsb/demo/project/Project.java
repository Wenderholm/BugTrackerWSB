package wsb.demo.project;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Boolean enable = true;

//    Set<Issue> issues = new HashSet<>();

    @Column(nullable = false)
    String code;

    @Column(nullable = false)
    String description;

    @Column
    Date dateCreated;

    public Project(String name, Boolean enable, String code, String description) {
        this.name = name;
        this.enable = enable;
        this.code = code;
        this.description = description;
        this.dateCreated = new Date();
    }

    public Project(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
