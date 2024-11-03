package tn.louay.recruitme.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DBFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

    private int createdBy;

    public DBFile(String fileName, String fileType, byte[] data, int createdBy) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
        this.createdBy = createdBy;
    }

}