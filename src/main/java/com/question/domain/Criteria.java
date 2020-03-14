package com.question.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;
    private int allNum;
    private int paging;

    private String type;
    private String keyword;


    //default 값, 10개씩 1페이지
    public Criteria(){
        this(1,10);

    }
    public Criteria(int pageNum, int amount){
        this.pageNum=pageNum;
        this.amount = amount;
        this.allNum = pageNum * amount;
        this.paging = (pageNum-1) * amount;
    }

    public String[] getTypeArr() {
        return type == null? new String[] {} : type.split("");
    }

    public String getListLink(){
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                .queryParam("pageNum",this.pageNum)
                .queryParam("amount",this.amount)
                .queryParam("type",this.getType())
                .queryParam("keyword",this.getKeyword());
        return builder.toUriString();
    }
}
