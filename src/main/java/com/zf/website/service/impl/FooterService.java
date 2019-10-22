package com.zf.website.service.impl;

import com.zf.website.bean.Footer;
import com.zf.website.bean.Link;
import com.zf.website.config.FileUploadConfig;
import com.zf.website.mapper.FooterMapper;
import com.zf.website.service.IFooterService;
import com.zf.website.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Model:
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/27 8:59
 */
@Service
@Transactional
public class FooterService implements IFooterService {

    @Resource
    private FooterMapper footerMapper;

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Override
    public boolean editFooter(Footer footer, MultipartFile file) throws IOException {
        Footer footer1 = footerMapper.getFooter();
        if (BeanUtil.isNull(footer) && file == null) {
            if (footer1 != null) {
                File f = new File(footer1.getQrRealPath());
                if (f != null) {
                    f.delete();
                }
            }
            footerMapper.deleteAllFooter();
            return true;
        }
        if (footer != null && file == null) {
            if(footer.getId() != null)
                return footerMapper.updateFooter(footer) > 0 ? true : false;
            else
                return footerMapper.saveFooter(footer) > 0 ? true : false;
        }
        if (footer != null && file != null) {
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            String realPath = fileUploadConfig.getRealPath() + "" + fileName;
            String relativePath = fileUploadConfig.getRelativePath() + "" + fileName;
            File filePath = new File(fileUploadConfig.getRealPath());
            file.transferTo(new File(realPath));
            footer.setQrRealPath(realPath);
            footer.setQrRelativePath(relativePath);
            if (!filePath.exists())
                filePath.mkdirs();
            if (footer.getId() != null) {
                File f = new File(footer1.getQrRealPath());
                boolean flag = false;
                if (f != null) {
                    flag = f.delete();
                }
                return flag && footerMapper.updateFooter(footer) > 0 ? true : false;
            } else {
                return footerMapper.saveFooter(footer) > 0 ? true : false;
            }
        }
        return false;
    }

    @Override
    public Footer getFooter() {
        return footerMapper.getFooter();
    }

    @Override
    public boolean saveLinks(List<Link> links) {
        footerMapper.deleteAllLinks();
        if (links != null) {
            for (int i = 0; i < links.size(); i++) {
                Link link = links.get(i);
                if ((link.getNickName().isEmpty() || link.getNickName() == null) && (link.getUrl().isEmpty() || link.getUrl() == null)) {
                    links.remove(i);
                }
            }
            return footerMapper.saveLinks(links) > 0 ? true : false;
        } else {
            return true;
        }
    }

    @Override
    public List<Link> listLinks() {
        return footerMapper.listLinks();
    }
}
