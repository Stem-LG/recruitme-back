package tn.louay.recruitme.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class uploadFileResponseDTO {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
