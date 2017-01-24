package com.lessons.db.phonebook.model;

/**
 * Phonebook record
 */
public class Record {

    private String phone;
    private String name;

    public Record() {}

    public Record(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
