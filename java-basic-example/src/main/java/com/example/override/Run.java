package com.example.override;

import com.google.common.base.MoreObjects;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Run {
    public static void main(String[] args) {
        System.out.println("------------------重写的对象-------------------");
        User user1 = new User("zhangsan", 18, "010000");
        User user2 = new User("zhangsan", 18, "010000");
        System.out.println("equel :" + user1.equals(user2));//true
        /**
         * user1.equals(user2) 成立 所以可以如下方式使用
         */
        Map<User, Integer> map = new HashMap<>();
        map.put(user1, 1);
        System.out.println("value:" + map.get(user2).toString());
        System.out.println("user1->hashCode:" + user1.hashCode() + ",user2->hashCode:" + user2.hashCode());

        System.out.println("------------------未重写的对象------------------");
        /**
         * 此处未重写 equals和hashCode仍然相等
         * 分析原因：
         * 因为user1s和user2s的hashCode输出的值是一样的
         * Object的equals的源码是：
         * public boolean equals(Object obj) {
         *         return (this == obj);
         * }
         * 由于hashcode相同 == 会认为是同一个对象
         */
        UserSimple user1s = new UserSimple("zhangsan", 18, "010000");
        UserSimple user2s = new UserSimple("zhangsan", 18, "010000");
        System.out.println("equel :" + user1s.equals(user2s));
        System.out.println("user1s->hashCode:" + user1s.hashCode() + ",user2s->hashCode:" + user2s.hashCode());
    }


    /**
     * 排序测试
     */
    @Test
    public void testSort() {
        Person[] persons = new Person[2];
        persons[0] = new Person("Ma", "Jack", 12345);
        persons[1] = new Person("Ma", "Pony", 65432);
        Arrays.stream(persons).sorted(Person::compareTo).forEach(person -> System.out.println(person.getFirstName()));
    }

    /**
     * 排序测试，使用guaua的ComparisonChain
     */
    @Test
    public void testSortGuaua() {
        ComparisonChainPerson[] persons = new ComparisonChainPerson[2];
        persons[0] = new ComparisonChainPerson("Ma", "Jack", 12345);
        persons[1] = new ComparisonChainPerson("Ma", "Pony", 65432);
        Arrays.stream(persons).sorted(ComparisonChainPerson::compareTo).forEach(person -> System.out.println(person.getFirstName()));
    }

    @Test
    public void toStringPrint() {
        UserSimple user1s = new UserSimple("zhangsan", 18, "010000");
        System.out.println(user1s.toString());
    }
}
