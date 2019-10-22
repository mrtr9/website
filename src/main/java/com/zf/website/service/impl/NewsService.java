package com.zf.website.service.impl;

import com.zf.website.bean.Banner;
import com.zf.website.bean.News;
import com.zf.website.mapper.NewsMapper;
import com.zf.website.service.INewsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/10/18 11:12
 */
@Service
@Transactional
public class NewsService implements INewsService {

    @Resource
    private NewsMapper newsMapper;

    @Override
    public boolean saveBanner(Banner banner) {
        return newsMapper.saveBanner(banner) > 0;
    }

    @Override
    public boolean deleteBanner(Integer id) {
        return newsMapper.deleteBanner(id) > 0;
    }

    @Override
    public boolean updateBanner(Banner banner) {
        return newsMapper.updateBanner(banner) > 0;
    }

    @Override
    public boolean usedBanner(Integer id) {
        return newsMapper.usedBanner(id) > 0;
    }

    @Override
    public boolean unsedBanner() {
        return newsMapper.unsedBanner() > 0;
    }

    @Override
    public Banner getBanner(Integer id) {
        return newsMapper.getBanner(id);
    }

    @Override
    public Banner getUsedBanner() {
        return newsMapper.getUsedBanner();
    }

    @Override
    public List<Banner> getUsedListBanner() {
        return newsMapper.getUsedListBanner();
    }

    @Override
    public List<Banner> listBanner() {
        return newsMapper.listBanner();
    }

    @Override
    public boolean saveNews(News news) {
        return newsMapper.saveNews(news) > 0;
    }

    @Override
    public boolean deleteNews(Integer id) {
        return newsMapper.deleteNews(id) > 0;
    }

    @Override
    public boolean updateNews(News news) {
        return newsMapper.updateNews(news) > 0;
    }

    @Override
    public int countNews() {
        return newsMapper.countNews();
    }

    @Override
    public News getNews(Integer id) {
        return newsMapper.getNews(id);
    }

    @Override
    public News getPreNews(LocalDate date) {
        return newsMapper.getPreNews(date);
    }

    @Override
    public News getNextNews(LocalDate date) {
        return newsMapper.getNextNews(date);
    }

    @Override
    public List<News> listPageNews(Integer page, Integer limit) {
        page = (page - 1) * limit;
        return newsMapper.listPageNews(page, limit);
    }

    @Override
    public List<News> listAllNews() {
        return newsMapper.listAllNews();
    }


}
