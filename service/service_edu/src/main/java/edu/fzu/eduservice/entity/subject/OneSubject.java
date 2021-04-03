package edu.fzu.eduservice.entity.subject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JohnCarraway
 * @create 2021-01-17 10:28
 */

// 一级分类
public class OneSubject {

    private String id;

    private String title;

    // 一级分类中包含多个二级分类
    private List<TwoSubject> children = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public List<TwoSubject> getChildren() {
        return children;
    }

    public void setChildren(List<TwoSubject> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "OneSubject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", children=" + children +
                '}';
    }
}
