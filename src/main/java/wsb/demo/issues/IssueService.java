package wsb.demo.issues;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import wsb.demo.auth.Person;
import wsb.demo.enums.State;
import wsb.demo.project.Project;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;
    private final EntityManager entityManager;


    public IssueService(IssueRepository issueRepository, EntityManager entityManager) {
        this.issueRepository = issueRepository;
        this.entityManager = entityManager;
    }

    public void softDelete(Issue issue){
        issue.setEnable(false);
        issueRepository.save(issue);
    }
//    List<Issue> findAllEnableIssue(Specification<Issue> issueSpecification){
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Issue> query = builder.createQuery(Issue.class);
//        Root<Issue> issueRoot = query.from(Issue.class);
//        CriteriaQuery<Issue> select = query.select(issueRoot);
//        Predicate isEnable = builder.equal(issueRoot.get("enable"), true);
//        TypedQuery<Issue> typedQuery = entityManager.createQuery(select.where(isEnable));
//        List<Issue> issues = typedQuery.getResultList();
//        return issues;
//    }
}
