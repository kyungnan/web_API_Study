package hellospring.demo.service;

import hellospring.demo.file.FileUploadProperties;
import hellospring.demo.file.exception.FileDownloadException;
import hellospring.demo.file.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImpl implements FileService {

    private final Path fileLocation;

    @Autowired
    public FileServiceImpl(FileUploadProperties properties) {
        this.fileLocation = Paths.get(properties.getUploadDir()).toAbsolutePath().normalize();

        try{
            Files.createDirectories(this.fileLocation);
        } catch (Exception e){
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    // 파일 저장하는 코드
    public String fileUpload(MultipartFile multipartFile){
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        try{
            Path targetLocation = this.fileLocation.resolve(fileName);
            Files.copy(multipartFile.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e){
            throw new FileUploadException("["+fileName+"] 파일 업로드에 실패하였습니다. 다시 시도하세요.");
        }
    }

    //파일 다운로드하는 코드
    @Override
    public Resource fileDownload(String fileName) {
        try {
            Path path = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(path.toUri());

            if(resource.exists()){
                return resource;
            } else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        } catch (MalformedURLException e){
            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다."+ e);
        }
    }

}
