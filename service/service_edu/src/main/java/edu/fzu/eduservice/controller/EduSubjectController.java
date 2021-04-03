package edu.fzu.eduservice.controller;


import edu.fzu.commonutils.Result;
import edu.fzu.eduservice.entity.subject.OneSubject;
import edu.fzu.eduservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author JohnRambo
 * @since 2021-01-10
 */
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    // 添加课程分类
    // 获取上传的文件, 然后读取文件的内容
    @RequestMapping(value = "addSubject", method = RequestMethod.POST)
    public Result addSubject(MultipartFile file){
        // 获取上传过来的文件
        subjectService.saveSubject(file, subjectService);
        return Result.ok();
    }

    /**
     * 课程分类列表(树形)
     * @return
     */
    @RequestMapping(value = "getAllSubject", method = RequestMethod.GET)
    public Result getAllSubject(){

        // list集合中的泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return Result.ok().add("list", list);
    }

}

