package wsb.demo.file;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    final FileStorageService fileStorageService;

    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/add")
    public String addFile(){
        return "file/upload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes){
        if (file.isEmpty()){
            attributes.addFlashAttribute("message","Nie można dodawc pustych plików");
        } else {
            try {
                fileStorageService.store(file);
                attributes.addFlashAttribute("message", "Brawo udało się");
            } catch (IOException e) {
                e.printStackTrace();
                attributes.addFlashAttribute("message", "nie udało się");
            }
        }
    return "file/upload";
    }
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id){
        FileDB fileDB = fileStorageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
