package com.pproject.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.pproject.app.entity.Notice;

import java.util.List;

public interface NoticeRepository extends CrudRepository<Notice, Integer> {

    List<Notice> findNoticesByTitleContaining(String contain);

}
