package com.example.homework.dto;

import com.example.homework.entity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class ArticleResponseDto {
    private String title;
    private String name;
    private String content;
    //private String password;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    public ArticleResponseDto(Article article){
        this.title = article.getTitle();
        this.name = article.getName();
        this.content = article.getContent();
        this.createdAt = article.getCreatedAt();
        this.modifiedAt = article.getModifiedAt();
        System.out.println("getCreatedAt() = " + getCreatedAt());

    }
}
