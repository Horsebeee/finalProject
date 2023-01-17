package com.study.app.dto;

import com.study.app.domain.Board;
import com.study.app.domain.User;

import java.time.LocalDateTime;

public record BoardDto(
        Long boardNo,
        UserDto userDto,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {
    public static BoardDto of(UserDto userDto,String content){
        return new BoardDto(null,userDto,content,null,null,null,null);
    }

    public static BoardDto of(Long boardNo, UserDto userDto, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new BoardDto(boardNo, userDto, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }
    public static BoardDto from(Board entity) {
        return new BoardDto(
                entity.getBoardNo(),
                UserDto.from(entity.getUser()),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
    public Board toEntity(User user) {
        return Board.of(
                user,
                content);
    }


}
