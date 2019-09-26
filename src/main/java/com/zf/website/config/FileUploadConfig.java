package com.zf.website.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Model:文件上传配置类
 * Author:Tr9(韩峰)
 * Description:
 * Time: 2019/9/24 9:32
 */
@Component
@ConfigurationProperties(prefix = "fileupload")
@Data
public class FileUploadConfig {

    private String relativePath;
    private String realPath;
    private String logoRelativePath;
    private String logoRealPath;
    private String bannerRelativePath;
    private String bannerRealPath;

}
