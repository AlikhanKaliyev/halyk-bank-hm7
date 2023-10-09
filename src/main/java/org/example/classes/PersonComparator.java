package org.example.classes;

import org.example.exception.SortingException;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2){
        if (p1 == null || p2 == null) {
            throw new RuntimeException(new SortingException("Unable to sort list"));
        }
        return p1.getName().compareTo(p2.getName());
    }
}
