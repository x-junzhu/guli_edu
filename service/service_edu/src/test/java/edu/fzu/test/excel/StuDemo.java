package edu.fzu.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author JohnCarraway
 * @create 2021-01-10 14:23
 */
public class StuDemo {

    // 设置表头名称
    @ExcelProperty(value = "学生编号", index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名", index = 1)
    private String sname;

    public StuDemo() {
    }

    public StuDemo(Integer sno, String sname) {
        this.sno = sno;
        this.sname = sname;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    @Override
    public String toString() {
        return "StuDemo{" +
                "sno=" + sno +
                ", sname='" + sname + '\'' +
                '}';
    }
}
