package com.aphrodite.manageractivity.entity;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Aphrodite on 2017/11/26.
 */

public class Dog extends RealmObject {
  @PrimaryKey
  private String id;

  private String name;
  private int age;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Dog{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", age=" + age +
      '}';
  }
}
