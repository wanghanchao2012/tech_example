package com.example.objovrs;

import com.example.objovrs.entity.Person;
import com.example.objovrs.entity.PersonOvrCompareTo;
import com.example.objovrs.entity.UserOvrHashCode;
import com.example.objovrs.entity.UserPrettyToString;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ExampleInTest {
    /**
     * 当使用HashMap等散是，且需要将对象作为key来做get/put/equals等操作是必须要同时重写实体的equals和hashCode的
     * 比如get时需要用hashCode来得到hash，且需要用equals来对key来做比较
     * 如单独使用对象时，不必须要同时重写equals和hashCode ，但是违反规范（所以还是建议同时重写）
     * 当重写equals（不重写hashCode）时，两个equals对象相等，但此时hashCode却可能不想等，相反有同样的问题
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("------------------重写的对象-------------------");
        UserOvrHashCode user1 = new UserOvrHashCode("zhangsan", 18, "010000");
        UserOvrHashCode user2 = new UserOvrHashCode("zhangsan", 18, "010000");
        System.out.println("equal : " + user1.equals(user2));//true
        Map<UserOvrHashCode, String> map = new HashMap<>();
        map.put(user1, "user1");
        /**
         * 由于类UserOvrHashCode的equals和hashCode已重写（对象所有属性的值都相同那么产生hashCode一定相同）
         * 所以此处map(存入user1，用user2来获取，此时两个对象的属性值完全相同)可以get到
         */
        System.out.println("key : " + user2.toString());
        System.out.println("value : " + map.get(user2));
        System.out.println("user1->hashCode : " + user1.hashCode() + ",user2->hashCode:" + user2.hashCode());

        //prettyPrintUser();
    }

    private static void prettyPrintUser() {
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
        UserPrettyToString user1s = new UserPrettyToString("zhangsan", 18, "010000");
        UserPrettyToString user2s = new UserPrettyToString("zhangsan", 18, "010000");
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
        PersonOvrCompareTo[] persons = new PersonOvrCompareTo[2];
        persons[0] = new PersonOvrCompareTo("Ma", "Jack", 12345);
        persons[1] = new PersonOvrCompareTo("Ma", "Pony", 65432);
        Arrays.stream(persons).sorted(PersonOvrCompareTo::compareTo).forEach(person -> System.out.println(person.getFirstName()));
    }

    @Test
    public void toStringPrint() {
        UserPrettyToString user1s = new UserPrettyToString("zhangsan", 18, "010000");
        System.out.println(user1s.toString());
    }
}
