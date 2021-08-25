package hellospring.demo.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String fileUpload(MultipartFile file);

    Resource fileDownload(String fileName);
}
