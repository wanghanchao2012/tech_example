package com.example.override;

import com.google.common.collect.ComparisonChain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Person类的逻辑是先比较Person.lastName, 如果相等再比较Person.firstName,
 * 如果前面的条件还是相等, 就再比较Person.zipCode. 代码的含义相当清晰, 只是有不少的模板代码,
 * 如果能减少这些模板代码, 那就更好了. 幸运的是, Guava 提供了一个 ComparisonChain来处理这些模板逻辑,
 * 应用ComparisonChain之后的代码如下:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComparisonChainPerson implements Comparable<ComparisonChainPerson> {
    private String lastName;
    private String firstName;
    private int zipCode;

    @Override
    public int compareTo(ComparisonChainPerson that) {
        return ComparisonChain.start()
                .compare(this.lastName, that.lastName)
                .compare(this.firstName, that.firstName)
                .compare(this.zipCode, that.zipCode)
                .result();
    }
}