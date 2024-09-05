package org.example.models;

public class Person {
     Integer Id;
     String Name;
     Integer Age;

    public Person(Integer id, String name, Integer age) {
        Id = id;
        Name = name;
        Age = age;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Age=" + Age +
                '}';
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }
}
