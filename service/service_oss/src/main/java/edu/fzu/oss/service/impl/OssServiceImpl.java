package edu.fzu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import edu.fzu.oss.service.OssService;
import edu.fzu.oss.utils.ConstantPropertiesUtil;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author JohnCarraway
 * @create 2021-01-02 23:18
 */
@Service
public class OssServiceImpl implements OssService {

    /**
     * 上传图片到阿里云并返回头像地址
     * @param file
     * @return
     */
    @Override
    public String updateFileAvatar(MultipartFile file) {

        // 通过工具类获取访问的四个固定参数
        String endpoint = ConstantPropertiesUtil.END_POINT;
        String accessKeyId = ConstantPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtil.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtil.BUCKET_NAME;

        // 创建OSSClient实例
        OSS ossClient = null;
        try {
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 获取上传文件的输入流
            InputStream inputStream = file.getInputStream();

            // 获取文件名称
            String fileName = file.getOriginalFilename();

            // 1.在文件名称中添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid +  fileName;

            // 2.把上传的文件按照日期进行分类
            // 获取当前日期
            String currentTimePath = new DateTime().toString("yyyy/MM/dd");

            // 拼接按照时间分类的文件夹名称
            fileName = currentTimePath + "/" + fileName;
            // 调用oss方法实现文件上传
            // 第一个参数时bucket名称
            // 第二个参数是上传到oss的文件名称和路径
            // 第三个参数时上传的文件输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 上传文件的返回路径, 需要手动拼接
            // https://edu-guli-0514.oss-cn-beijing.aliyuncs.com
            // /2020/05/23/bebfdc02e007444b9b72b5ba5509be16banner2.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 关闭OSSClient
            ossClient.shutdown();
        }

    }
}
