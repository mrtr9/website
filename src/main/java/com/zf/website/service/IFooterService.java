package com.zf.website.service;

import com.zf.website.bean.Footer;
import com.zf.website.bean.Link;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IFooterService {

    boolean editFooter(Footer footer, MultipartFile file) throws IOException;

    Footer getFooter();

    boolean saveLinks(List<Link> links);

    List<Link> listLinks();
}
