package com.lessons.db.phonebook.db.file;

import com.lessons.db.phonebook.db.Store;
import com.lessons.db.phonebook.model.Record;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Service
@Scope(SCOPE_SINGLETON)
@Lazy
public class FileStore implements Store {

    public void add(Record record) {
        throw new RuntimeException("Not realized yet");
    }

    public List<Record> getByLetter(char letter) {
        throw new RuntimeException("Not realized yet");
    }

    public Record getByName(String name) {
        throw new RuntimeException("Not realized yet");
    }

}
