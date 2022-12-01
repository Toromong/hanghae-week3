package com.example.homework.controller;

import com.example.homework.dto.*;
import com.example.homework.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/save/article")
    public ResponseDto saveArticle(@RequestBody ArticleRequestDto requestDto){
        return articleService.saveArticle(requestDto);
    }
    @GetMapping("/get/articles")
    public ArticleListResponseDto getArticles(){
        return articleService.getArticles();
    }

    @GetMapping("/get/article")
    public ArticleResponseDto getArticle(@RequestParam Long id){
        return articleService.getArticle(id);
    }
    @PutMapping("/update/article/{id}")
    public ArticleResponseDto updateArticle(@PathVariable Long id, @RequestBody ArticleResponseDto requestDto){
    return articleService.updateArticle(id,requestDto);
    }
    @DeleteMapping("/delete/article/{id}")
    public ArticleDeleteResponseDto deleteArticle(@PathVariable Long id, @RequestBody ArticleDeleteRequestDto requestDto){
        return new ArticleDeleteResponseDto(articleService.deleteArticle(id, requestDto.getPassword()));
    }
}
