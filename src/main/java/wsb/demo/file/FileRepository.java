package wsb.demo.file;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface FileRepository extends JpaRepository<FileDB,Long> {
}
