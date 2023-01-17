package com.study.app.repository;

import com.study.app.domain.Board;
import com.study.app.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long> {

}
