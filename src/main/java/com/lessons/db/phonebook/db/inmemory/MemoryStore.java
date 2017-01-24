package com.lessons.db.phonebook.db.inmemory;

import com.lessons.db.phonebook.db.Store;
import com.lessons.db.phonebook.model.Record;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

import java.util.LinkedList;
import java.util.List;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_SINGLETON;

@Service
@Scope(SCOPE_SINGLETON)
@Lazy
public class MemoryStore implements Store {

    int alphabetFirstLetterCode = (int)'А';
    int alphabetLastLetterCode = (int)'Я';
    int size = alphabetLastLetterCode - alphabetFirstLetterCode + 1;
    private List<?> pages[] = new List<?>[size];

    @PostConstruct
    public void init() {
        for (int i = 0; i < size; i++) {
            pages[i] = new LinkedList<Record>();
        }
    }

    @Override
    public void add(Record record) {
        if (StringUtils.isEmpty(record.getName())) {
            throw new IllegalArgumentException("Incorrect parameter of name, must not be empty");
        }
        getByLetter(record.getName().charAt(0)).add(record);
    }

    @Override
    public List<Record> getByLetter(char letter) {
        int letterCode = (int)Character.toUpperCase(letter);
        int position = letterCode - alphabetFirstLetterCode;
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("Incorrect first letter of name, use letters а..яА..Я");
        }
        @SuppressWarnings("uncheked")
        List<Record> result = (List<Record>)pages[position];
        return result;
    }

    @Override
    public Record getByName(String name) {
        if (StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("Incorrect parameter for search, must not be empty");
        }
        return getByLetter(name.charAt(0)).stream()
                .filter(n -> n.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

}
