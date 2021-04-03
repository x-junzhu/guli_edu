package edu.fzu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.fzu.eduservice.entity.EduSubject;
import edu.fzu.eduservice.entity.excel.SubjectData;
import edu.fzu.eduservice.entity.subject.OneSubject;
import edu.fzu.eduservice.entity.subject.TwoSubject;
import edu.fzu.eduservice.mapper.EduSubjectMapper;
import edu.fzu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.fzu.eduservice.utils.SubjectExcelListener;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author JohnRambo
 * @since 2021-01-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 添加课程分类
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            // 获取文件的输入流
            InputStream inputStream = file.getInputStream();
            // 调用读取文件的方法
            EasyExcel.read(inputStream, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 课程分类列表(树形)
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 1.查询所有的一级分类parentid=0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        // 2.查询所有的二级分类parentid!=0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        // 创建list集合用于存储最终封装的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        // 3.分装一级分类
        // 查出来的所有一级分类list集合进行遍历，得到每一个一级分类对象，获取每一个一级分类的
        // 对象值，封装到要求的list集合中去
        for (int i = 0; i < oneSubjectList.size(); i++) {// 遍历oneSubjectList集合
            // 得到oneSubjectList中的每个EduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            // 把eduSubject对象的值取出放入oneSubject对象中
            // 多个oneSubject最后放到finalSubjectList中去
            OneSubject oneSubject = new OneSubject();
            // 字段较多时会多个get/set会比较繁琐
//            oneSubject.setId(eduSubject.getId());
//            oneSubject.setTitle(eduSubject.getTitle());
            // 功能是把eduSubject的对应属性值get出来然后在set到oneSubject中
            BeanUtils.copyProperties(eduSubject, oneSubject);

            // 在一级分类的循环里遍历查询二级分类
            // 创建一个list集合用于分装一级分类中的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();
            // 遍历二级分类list集合
            for (int m = 0; m < twoSubjectList.size(); m++) {
                EduSubject tSubject = twoSubjectList.get(m);
                // 判断二级分类的parent_id和一级分类的id是否相同
                if (tSubject.getParentId().equals(oneSubject.getId())){
                    //把tSubject的值复制到TwoSubject里面，然后在把TwoSubject放入finalSubjectList
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tSubject, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }

            // 把一级分类下面的所有二级分类放入一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);

            finalSubjectList.add(oneSubject);
        }

        // 4.分装二级分类
        return finalSubjectList;
    }
}
