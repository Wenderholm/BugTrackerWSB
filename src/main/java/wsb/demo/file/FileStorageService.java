package wsb.demo.file;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileStorageService {

    final FileRepository fileRepository;


    public FileStorageService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FileDB store(MultipartFile file) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        String type = file.getContentType();
        FileDB fileDB = new FileDB(filename,type,file.getBytes());
        return fileRepository.save(fileDB);
    }

    public FileDB getFile(Long id){
        return fileRepository.findById(id).get();
    }

}
