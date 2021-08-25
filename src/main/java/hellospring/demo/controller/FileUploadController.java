package hellospring.demo.controller;

import hellospring.demo.file.UloadedFile;
import hellospring.demo.service.FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("api/file")
@RequiredArgsConstructor
public class FileUploadController {

    private final FileService fileService;
    private Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/upload")
    public UloadedFile uploadRile(@RequestParam("file") MultipartFile multipartFile){
        String fileName = fileService.fileUpload(multipartFile);

        String fileDownloadUri = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UloadedFile(fileName, fileDownloadUri, multipartFile.getContentType(), multipartFile.getSize());
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        Resource resource = fileService.fileDownload(fileName);

        String contentType = null;
        try {
            contentType = request
                    .getServletContext()
                    .getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e){
            logger.info("Could not determine file type.");
        }

        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ resource.getFilename()+"\"")
                .body(resource);
    }
}
