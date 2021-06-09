package wsb.demo.issues;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import wsb.demo.auth.Person;
import wsb.demo.enums.State;
import wsb.demo.enums.Type;
import wsb.demo.project.Project;

@Getter
@Setter
@NoArgsConstructor
public class IssueFilter {

    Type type;
    State state;
    Project project;
    Person assignee;
    Issue enable;

    private Specification<Issue> isEnable(){
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("enable"), true);
    }

    private Specification<Issue> hasState() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("state"), state);
    }

    private Specification<Issue> hasProject() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("project"), project);
    }

    private Specification<Issue> hasAssignee() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("assignee"), assignee);
    }

    private Specification<Issue> hasType() {
        return (issueRoot, query, builder) -> builder.equal(issueRoot.get("type"), type);
    }

    public Specification<Issue> buildQuery() {
        Specification<Issue> spec = Specification.where(isEnable());

        if (project != null) {
            spec = spec.and(hasProject());
        }

        if (assignee != null) {
            spec = spec.and(hasAssignee());
        }

        if (state != null) {
            spec = spec.and(hasState());
        }

        if (type != null) {
            spec = spec.and(hasType());
        }

        return spec;
    }

}
