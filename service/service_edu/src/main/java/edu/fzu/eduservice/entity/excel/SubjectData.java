package edu.fzu.eduservice.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * @author JohnCarraway
 * @create 2021-01-10 15:02
 */
public class SubjectData {

    @ExcelProperty(index = 0)
    private String oneSubject;

    @ExcelProperty(index = 1)
    private String twoSubject;

    public SubjectData() {
    }

    public SubjectData(String oneSubject, String twoSubject) {
        this.oneSubject = oneSubject;
        this.twoSubject = twoSubject;
    }

    public String getOneSubject() {
        return oneSubject;
    }

    public void setOneSubject(String oneSubject) {
        this.oneSubject = oneSubject;
    }

    public String getTwoSubject() {
        return twoSubject;
    }

    public void setTwoSubject(String twoSubject) {
        this.twoSubject = twoSubject;
    }
}
