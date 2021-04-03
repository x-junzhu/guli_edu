package edu.fzu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author JohnCarraway
 * @create 2021-01-02 23:18
 */
public interface OssService {
    // 上传图片到阿里云并返回头像地址
    String updateFileAvatar(MultipartFile file);
}
