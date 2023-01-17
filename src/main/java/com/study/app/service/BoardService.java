package com.study.app.service;

import com.study.app.dto.BoardDto;
import com.study.app.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;

    // 게시글 전부 보여주기
    @Transactional(readOnly = true)
    public Page<BoardDto> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable).map(BoardDto::from);
    }



}
