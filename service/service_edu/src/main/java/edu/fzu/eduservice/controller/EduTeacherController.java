package edu.fzu.eduservice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.fzu.commonutils.Result;
import edu.fzu.eduservice.client.OssClient;
import edu.fzu.eduservice.entity.EduTeacher;
import edu.fzu.eduservice.entity.vo.TeacherQuery;
import edu.fzu.eduservice.service.EduTeacherService;
import edu.fzu.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author JohnRambo
 * @since 2021-01-02
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private OssClient ossClient;

    @ApiOperation(value = "查找所有讲师信息列表")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAllTeacher(){

        List<EduTeacher> teacherList = eduTeacherService.list(null);
        return Result.ok().add("teacherList", teacherList);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)
                                @PathVariable("id") String id){

        boolean flag = eduTeacherService.removeById(id);

        if (flag){
            return Result.ok();
        }else{
            return Result.error();
        }
    }

    /**
     * 分页查询讲师的方法
     * @param current 当前页
     * @param limit 当前页的记录数
     * @return
     */
    @RequestMapping(value = "queryALlByPage/{current}/{limit}", method = RequestMethod.GET)
    public Result queryAllTeacher(@PathVariable("current") long current,
                                  @PathVariable("limit") long limit){
        // 创建page对象
        Page<EduTeacher> pageTeacherList = new Page<>(current, limit);

        try{
            int i = 10 / 0;
        } catch (Exception e){
            // 执行自定义异常
            throw new GuliException(20001, "执行了自定义异常:" + e.getMessage());
        }

        // 调用分页方法
        eduTeacherService.page(pageTeacherList, null);
        long total = pageTeacherList.getTotal();//总记录数
        List<EduTeacher> teacherList = pageTeacherList.getRecords();//每页数据的list集合
//        Map map = new HashMap<>();
//        map.put("total", total);
//        map.put("rows", teacherList);
        return Result.ok().add("total", total).add("rows", teacherList);
    }

    // 讲师的条件查询带分页
    @RequestMapping(value = "pageTeacherCondition/{current}/{limit}", method = RequestMethod.POST)
    public Result queryTeacherByCondition(@PathVariable long current,
                                          @PathVariable long limit,
                                          @RequestBody(required = false) TeacherQuery teacherQuery){
        // 创建page对象
        Page<EduTeacher> pageTeacherList = new Page<>(current, limit);
        // 构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        // 多条件组合查询, 判断条件是否为空, 如果不为空则拼接条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.gt("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create", end);
        }

        // 根据创建时间排序
        wrapper.orderByDesc("gmt_create");
        // 调用查询实现分页方法
        eduTeacherService.page(pageTeacherList, wrapper);

        long total = pageTeacherList.getTotal();//总记录数
        List<EduTeacher> teacherList = pageTeacherList.getRecords();//每页数据的list集合
        return Result.ok().add("total", total).add("rows", teacherList);
    }

    /**
     * 添加讲师
     * @param eduTeacher
     * @return
     */
    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag){
            return Result.ok();
        } else{
            return Result.error();
        }

    }

    /**
     * 根据讲师id查询讲师信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTeacher/{id}", method = RequestMethod.GET)
    public Result getTeacher(@PathVariable String id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return Result.ok().add("teacher", teacher);
    }

    /**
     * 修改讲师信息
     * @param eduTeacher
     * @return
     */
    @RequestMapping(value = "updateTeacher", method = RequestMethod.POST)
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return Result.ok();
        } else{
            return Result.error();
        }
    }

    @RequestMapping(value = "/calledFeign", method = RequestMethod.GET)
    public Result feignCalled(){
        Result result = ossClient.viewOssInfo();
        return Result.ok().add("result", result);
    }


}

