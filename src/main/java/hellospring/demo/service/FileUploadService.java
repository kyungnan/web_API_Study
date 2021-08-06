package hellospring.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public void uploadToLocal(MultipartFile file);

}
