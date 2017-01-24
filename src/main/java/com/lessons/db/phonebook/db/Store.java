package com.lessons.db.phonebook.db;

import com.lessons.db.phonebook.model.Record;

import java.util.List;

/**
 * Interface to services stores phone book records
 */
public interface Store {

    void add(Record record);

    List<Record> getByLetter(char letter);

    Record getByName(String name);

}
