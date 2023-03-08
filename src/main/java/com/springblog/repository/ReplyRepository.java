package com.springblog.repository;

import com.springblog.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

  @Modifying
  @Query(value = "insert into reply (userId, blogId, content, createDate) values (?1, ?2, ?3, now())",nativeQuery = true)
  int saveForNateiveQuery(int userId, int blogId, String content);
}
