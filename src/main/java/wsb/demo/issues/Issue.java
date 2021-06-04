package wsb.demo.issues;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import wsb.demo.auth.Person;
import wsb.demo.enums.Priority;
import wsb.demo.enums.State;
import wsb.demo.enums.Type;
import wsb.demo.project.Project;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class Issue {

    @Id
    @GeneratedValue
    Long id;

    @NotEmpty
    @Size(min =5, max=50)
    @Column(nullable = false)
    String title;

    @NotEmpty
    @Size(min =5, max=250)
    @Column(columnDefinition = "TEXT")
    String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    State state = State.TODO;

    @ManyToOne()
    @JoinColumn(name = "assignee_id")
    Person assignee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    Project project;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Priority priority = Priority.EASY;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    Type type = Type.BUG;

    @Column(nullable = false)
    Boolean enable = true;

    public Issue(@NotEmpty @Size(min = 5, max = 50) String title, @NotEmpty @Size(min = 5, max = 250) String content) {
        this.title = title;
        this.content = content;
    }
}
