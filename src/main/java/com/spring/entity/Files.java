package com.spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "files")
@ToString
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Files {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private  int status;

    private  String filename;

    private  String o_name;

    private  String desc_;
}
