package com.zf.website.service;

import com.zf.website.bean.Banner;
import com.zf.website.bean.News;

import java.time.LocalDate;
import java.util.List;

public interface INewsService {

    boolean saveBanner(Banner banner);

    boolean deleteBanner(Integer id);

    boolean updateBanner(Banner banner);

    boolean usedBanner(Integer id);

    boolean unsedBanner();

    Banner getBanner(Integer id);

    Banner getUsedBanner();

    List<Banner> getUsedListBanner();

    List<Banner> listBanner();

    boolean saveNews(News news);

    boolean deleteNews(Integer id);

    boolean updateNews(News news);

    int countNews();

    News getNews(Integer id);

    News getPreNews(LocalDate date);

    News getNextNews(LocalDate date);

    List<News> listPageNews(Integer page,Integer limit);

    List<News> listAllNews();
}
