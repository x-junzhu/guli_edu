package edu.fzu.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JohnCarraway
 * @create 2021-01-10 14:26
 */
public class TestEasyExcel {

    public static void main(String[] args) {
        // 实现excel的写操作
        // 1.设置写入的文件夹名称和路径
        String fileName = "D:\\guli_log\\student.xlsx";
        // 2.调用easyexcel中的写入方法
        EasyExcel.write(fileName, StuDemo.class).sheet("学生列表").doWrite(getData());
    }

    @Test
    public void testReadExcelData(){
        // 实现excel的读操作
        // 1.设置写入的文件夹名称和路径
        String fileName = "D:\\guli_log\\student.xlsx";
        // 2.读取文件内容
        EasyExcel.read(fileName, StuDemo.class, new ExcelListener()).sheet("学生列表").doRead();

    }

    public static List<StuDemo> getData(){
        List<StuDemo> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++){
            StuDemo stuDemo = new StuDemo();
            stuDemo.setSno(i);
            stuDemo.setSname("mary" + i);
            list.add(stuDemo);
        }
        return  list;
    }
}
