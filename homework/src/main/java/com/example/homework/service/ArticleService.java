package com.example.homework.service;

import com.example.homework.dto.ArticleListResponseDto;
import com.example.homework.dto.ArticleRequestDto;
import com.example.homework.dto.ArticleResponseDto;
import com.example.homework.dto.ResponseDto;
import com.example.homework.entity.Article;
import com.example.homework.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    @Transactional
    public ResponseDto saveArticle(ArticleRequestDto requestDto) {
        Article article = new Article(requestDto);
        articleRepository.save(article);
        return new ResponseDto("글 등록 완료", HttpStatus.OK.value());
    }
    @Transactional(readOnly = true)
    public ArticleListResponseDto getArticles() {
        ArticleListResponseDto articleListResponseDto = new ArticleListResponseDto();
        List<Article> articles = articleRepository.findAll();
        for(Article article :articles){
            articleListResponseDto.addArticle(new ArticleResponseDto(article));
        }
        return articleListResponseDto;
    }
    @Transactional(readOnly = true)
    public ArticleResponseDto getArticle(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("글이없다")
        );
        return new ArticleResponseDto(article);
    }
    @Transactional
    public ArticleResponseDto updateArticle(Long id, ArticleResponseDto responseDto) {
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("글이 없다")
        );
        article.update(responseDto);
        return new ArticleResponseDto(article);
    }
    @Transactional
    public boolean deleteArticle(Long id,String password){
        if (articleRepository.existsByIdAndPassword(id, password)) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public ResponseDto deleteCourse(Long id) {
        Article article = articleRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("글이 없다")
        );
        articleRepository.delete(article);
        return new ResponseDto("삭제 성공", HttpStatus.OK.value());
    }
}
