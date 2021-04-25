package wsb.demo.project;

import org.springframework.stereotype.Service;
import wsb.demo.auth.Person;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void softDelete(Project project){
        project.setEnable(false);
        projectRepository.save(project);
    }
}
