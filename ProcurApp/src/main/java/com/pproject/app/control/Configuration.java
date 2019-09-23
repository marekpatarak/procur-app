package com.pproject.app.control;

import com.pproject.app.entity.Notice;
import com.pproject.app.entity.NoticeType;
import com.pproject.app.repository.NoticeTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@org.springframework.context.annotation.Configuration
public class Configuration {

    private Set<NoticeType> noticeTypeSet = new HashSet<>();

    @Autowired
    public NoticeTypeRepository noticeTypeRepository;

    @PostConstruct
    public void init() {
        update();
    }

    public void update() {
        noticeTypeSet.clear();
        List<NoticeType> noticeTypeList = (List<NoticeType>)noticeTypeRepository.findAll();
        noticeTypeList.forEach(x -> noticeTypeSet.add(x));

    }

    public Set<NoticeType> getNoticeTypeSet() {
        return noticeTypeSet;
    }

}
