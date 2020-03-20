package com.question.service;

import com.question.domain.GroupCateVO;
import com.question.domain.GroupVO;
import com.question.mapper.GroupMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {

    @Setter(onMethod_ = @Autowired)
    private GroupMapper mapper;


    @Override
    public List<GroupVO> getLank() {

         return mapper.getLank();
    }

    @Override
    public List<GroupVO> getLank2() {

        return mapper.getLank2();
    }

    @Override
    public List<GroupCateVO> getCate() {

        return mapper.getCate();
    }

    @Override
    public String readGroupCate(GroupVO group) {

        List<GroupCateVO> groupcate =  mapper.readGroupCate(group.getGroup_code());

        String cates="";

        for(int i =0; i< groupcate.size();i++){
            if(i==2)
                break;
            cates += " #"+ groupcate.get(i).getCate_name();
        }

        return cates;
    }


}



