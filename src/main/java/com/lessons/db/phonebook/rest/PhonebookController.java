package com.lessons.db.phonebook.rest;

import com.lessons.db.phonebook.db.Store;
import com.lessons.db.phonebook.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/phonebook")
public class PhonebookController {

    private static final String ACCEPT_JSON_UTF_8 = "Accept=application/json;charset=UTF-8";

    private static final String GET_BY_NAME = "/get/{name}";
    private static final String GET_BY_FIRST_LETTER = "/get/page/{letter}";

    @Autowired
    Store store;

    @RequestMapping(method = RequestMethod.POST, headers = ACCEPT_JSON_UTF_8)
    public void add(@RequestBody Record record) {
        store.add(record);
    }

    @RequestMapping(path = GET_BY_FIRST_LETTER, method = RequestMethod.GET)
    public List<Record> getByFirstLetter(@PathVariable("letter") char letter) {
        return store.getByLetter(letter);
    }

    @RequestMapping(path = GET_BY_NAME, method = RequestMethod.GET)
    public Record getByFirstLetter(@PathVariable("name") String name) {
        return store.getByName(name);
    }

}

