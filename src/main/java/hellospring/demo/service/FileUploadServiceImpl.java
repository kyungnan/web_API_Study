package hellospring.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileUploadServiceImpl implements FileUploadService{

    private String uploadFolderPath = "C:\\Users\\kyungnan\\Desktop\\study\\uploaded_";


    @Override
    public void uploadToLocal(MultipartFile file) {
        try {
            byte[] data = file.getBytes();
            Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());   //경로객체 생성
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
