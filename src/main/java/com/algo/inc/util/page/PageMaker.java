package com.algo.inc.util.page;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString(exclude = "pageList")
@Log
public class PageMaker<T>{
    private Page<T> result;

    private Pageable prevPage;
    private Pageable nextPage;

    private int currentPageNum;
    private int totalPageNum;

    private Pageable currentPage;

    private List<Pageable> pageList;


    public PageMaker(Page<T> result)
    {
        this.result = result;
        this.currentPage = result.getPageable();
        this.currentPageNum = currentPage.getPageNumber() + 1;
        this.totalPageNum = result.getTotalPages();
        this.pageList = new ArrayList<>();
        calcPages();
    }

    public void calcPages()
    {
        int tempEndNum = (int)(Math.ceil(this.currentPageNum/ 10.0) * 10);
        int startNum = tempEndNum - 9;

        Pageable startPage = this.currentPage;

        for (int i = startNum; i < this.currentPageNum; i++)
        {
            startPage = startPage.previousOrFirst();
        }

        this.prevPage = startPage.getPageNumber() <= 0 ? null : startPage.previousOrFirst();

        // page Number가 끝 페이지보다 크면
        if (this.totalPageNum < tempEndNum)
        {
            tempEndNum = this.totalPageNum;
            this.nextPage = null;
        }

        for (int i = startNum; i <= tempEndNum; i++)
        {
            pageList.add(startPage);
            startPage = startPage.next();
        }

        // 다음 페이지 정보 저장
        this.nextPage = startPage.getPageNumber() + 1 < totalPageNum ? startPage : null;
    }
}
