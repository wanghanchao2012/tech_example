package com.example.override;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person> {
    private String lastName;
    private String firstName;
    private int zipCode;

    @Override
    public int compareTo(Person other) {
        int cmp = lastName.compareTo(other.lastName);
        if (cmp != 0) {
            return cmp;
        }
        cmp = firstName.compareTo(other.firstName);
        if (cmp != 0) {
            return cmp;
        }
        return Integer.compare(zipCode, other.zipCode);
    }

}
