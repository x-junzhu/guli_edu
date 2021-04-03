package edu.fzu.test.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author JohnCarraway
 * @create 2021-01-10 14:40
 */
public class ExcelListener extends AnalysisEventListener<StuDemo> {

    // 一行一行读取数据, 默认是从excel的第二行开始读取, 第一行作为列名
    @Override
    public void invoke(StuDemo stuDemo, AnalysisContext analysisContext) {
        System.out.println("invoke methods " + stuDemo);
    }

    // 读取表头的方法
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息=" + headMap);
    }

    // 读取完成后需要做的事情
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
