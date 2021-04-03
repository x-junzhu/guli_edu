package edu.fzu.oss.controller;

import edu.fzu.commonutils.Result;
import edu.fzu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author JohnCarraway
 * @create 2021-01-02 23:18
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传头像到oss服务中
     * @param file
     * @return
     */
    @RequestMapping(value = "updateAvatar", method = RequestMethod.POST)
    public Result updateOssFile(MultipartFile file){

        String url = ossService.updateFileAvatar(file);

        return Result.ok().add("url", url);
    }

    @RequestMapping(value = "viewOssInfo", method = RequestMethod.GET)
    public Result viewOssInfo(){
        String ossInfo = "this is spring cloud called";
        return Result.ok().add("ossInfo", ossInfo);
    }
}
