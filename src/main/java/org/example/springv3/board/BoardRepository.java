package org.example.springv3.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 검색 query 
    @Query("select b from Board b where b.title like %:title% order by b.id desc")
    List<Board> mfindAll(@Param("title") String title);

    @Query("select b from Board b join fetch b.user left join fetch b.replies r left join fetch r.user where b.id=:id")
    Optional<Board> mFindByIdWithReply(@Param("id") int id);

    //@Query(value = "select * from board_tb bt inner join user_tb ut on bt.user_id = ut.id where bt.id=?", nativeQuery = true)
    @Query("select b from Board b join fetch b.user u where b.id=:id")
    Optional<Board> mFindById(@Param("id") int id);

}

