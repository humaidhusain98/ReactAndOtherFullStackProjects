package com.people.chat.Model;

import java.util.Objects;

public class Branch {
    Integer id;
    String name; 


    public Branch() {
    }

    public Branch(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Branch id(Integer id) {
        this.id = id;
        return this;
    }

    public Branch name(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Branch)) {
            return false;
        }
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id) && Objects.equals(name, branch.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}