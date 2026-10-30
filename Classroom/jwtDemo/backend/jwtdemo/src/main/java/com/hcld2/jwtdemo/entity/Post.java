package com.hcld2.jwtdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    private Long id ;
    @Column(nullable=false)
    private String title ;
    @Column(length = 2000)
    private  String content ;
    @Column(nullable=false)
    private String author ;
    private LocalDateTime createdAt = LocalDateTime.now();
}
