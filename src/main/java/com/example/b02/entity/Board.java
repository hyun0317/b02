package com.example.b02.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(length = 500,nullable = false)
    private String title;
    @Column(length = 5000,nullable = false)
    private String content;
    @Column(length = 50,nullable = false)
    private String writer;

    public void chang(String title, String content){

        this.title = title;
        this.content = content;


    }

}
