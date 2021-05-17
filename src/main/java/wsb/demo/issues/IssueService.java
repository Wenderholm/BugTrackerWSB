package wsb.demo.issues;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public void softDelete(Issue issue){
        issue.setEnable(false);
        issueRepository.save(issue);
    }
}
