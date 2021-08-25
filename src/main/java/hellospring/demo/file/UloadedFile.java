package hellospring.demo.file;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UloadedFile {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
