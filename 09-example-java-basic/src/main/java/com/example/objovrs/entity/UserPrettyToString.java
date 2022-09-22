package com.example.objovrs.entity;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrettyToString {
    private String name;
    private int age;
    private String passport;


    /**
     * 右键-->generate->toString()->choose Template
     *
     * <pre>
     *  toString
     * 《Effective Java》的条款10说到:
     *
     * Always override toString
     *
     * 也就是说, 《Effective Java》建议所有的类都重写toString()方法.
     * 其实toString()方法不是给程序看的, 而是给开发者自己看的. 据说,
     * 好看的toString()方法的输出结果可以让程序员更愉悦, 可见颜值处处都有用.
     * 比较常见的重写toString()的方式是把所有的字段拼接输出, 只不过手动拼接有点累.
     * 省心的是, Intellij Idea 为开发者提供了生成toString()的快捷方式
     * </pre>
     *
     * @return
     */
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("passport", passport)
                .toString();
    }
}