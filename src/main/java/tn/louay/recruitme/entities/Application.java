package tn.louay.recruitme.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.louay.recruitme.enums.ApplicationStatus;

import java.util.*;

import org.hibernate.annotations.CreationTimestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String motivation;

    private String resumeUrl;

    private int createdBy;

    @CreationTimestamp
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;

    @ManyToOne
    private JobOffer jobOffer;

    public Application(String name, String email, String motivation, String resumeUrl,
            JobOffer jobOffer) {
        this.name = name;
        this.email = email;
        this.motivation = motivation;
        this.resumeUrl = resumeUrl;
        this.createdAt = new Date();
        this.status = ApplicationStatus.pending;
        this.jobOffer = jobOffer;
    }
}