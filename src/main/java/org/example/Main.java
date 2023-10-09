package org.example;

import org.example.classes.Person;
import org.example.classes.PersonComparator;
import org.example.exception.AggregationException;
import org.example.exception.EmptyListException;
import org.example.exception.NoMatchingDataException;
import org.example.exception.SortingException;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    static List<Person> persons = new LinkedList<>();
    public static void main(String[] args) throws EmptyListException, NoMatchingDataException{
        System.out.println("First task:");
        executeFirstTask();
        System.out.println("Second task:");
        executeSecondTask();
        System.out.println("Third task:");
        executeThirdTask();
        System.out.println("Fourth task:");
        executeFourthTask();
    }

    public static void executeFirstTask() throws EmptyListException {
        try {
            if(persons.isEmpty()) throw new EmptyListException("List of persons is empty");
        } catch (EmptyListException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void executeSecondTask() throws NoMatchingDataException{
        persons.add(new Person("Alikhan", 21));
        persons.add(new Person("Timur", 22));
        persons.add(new Person("Olzhas", 22));
        persons.add(new Person("Magzhan", 20));
        long count = persons.stream().filter(person -> person.getYear() > 22).count();
        try {
            if(count == 0) throw new NoMatchingDataException("No data for such a request");
        } catch (NoMatchingDataException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void executeThirdTask(){
        persons.add(null);
        try {
            persons.stream().sorted(new PersonComparator()).forEach(System.out::println);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof SortingException) {
                System.out.println(e.getCause().getMessage());
            } else {
                throw e;
            }
        }
    }

    public static void executeFourthTask(){
        try {
            String aggregatedNames = persons.stream()
                    .map(person -> {
                        if (person == null) {
                            throw new RuntimeException(new AggregationException("Null value is detected, aggregation is unavailable"));
                        }
                        return person.getName();
                    })
                    .collect(Collectors.joining(", "));

            System.out.println(aggregatedNames);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof AggregationException) {
                System.out.println(e.getCause().getMessage());
            } else {
                throw e;
            }
        }
    }


}