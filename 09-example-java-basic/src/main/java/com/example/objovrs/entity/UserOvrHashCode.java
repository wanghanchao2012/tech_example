package com.example.objovrs.entity;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hashCode
 * 在《Effective Java》的条款9中说到:
 * <p>
 * Always override hashCode when you override equals
 * <p>
 * 就是说在你重写equals方法的时候, 记得重写hashCode方法,
 * 因为按照Java的约定, 如果两个对象通过调用equals方法判断是相等的话,
 * 它们调用hashCode()方法的返回结果也是一样的.
 * 《Effective Java》 给出的重写建议是把一个对象的所有字段进行计算取得一个hash值,
 * 示例代码如下:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOvrHashCode {
    private String name;
    private int age;
    private String passport;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserOvrHashCode user = (UserOvrHashCode) o;
        return age == user.age &&
                com.google.common.base.Objects.equal(name, user.name) &&
                com.google.common.base.Objects.equal(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(name, age, passport);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("passport", passport)
                .toString();
    }
}