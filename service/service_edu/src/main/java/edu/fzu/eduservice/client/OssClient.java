package edu.fzu.eduservice.client;

import edu.fzu.commonutils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author JohnCarraway
 * @create 2021-01-03 22:54
 */
@FeignClient("service-oss")
@Component
public interface OssClient {

    @RequestMapping(value = "/eduoss/fileoss/viewOssInfo", method = RequestMethod.GET)
    public Result viewOssInfo();
}
