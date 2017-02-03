package com.lessons.db.phonebook.rest;

import com.lessons.db.phonebook.db.Store;
import com.lessons.db.phonebook.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PhonebookController.ROOT_PATH)
public class PhonebookController {

    private static final String ACCEPT_JSON_UTF_8 = "Accept=application/json;charset=UTF-8";

    public static final String ROOT_PATH = "/phonebook";
    public static final String GET_BY_NAME_PATH = "/get";
    public static final String GET_BY_NAME = GET_BY_NAME_PATH + "/{name}";
    public static final String GET_BY_FIRST_LETTER_PATH = "/get/page";
    public static final String GET_BY_FIRST_LETTER = GET_BY_FIRST_LETTER_PATH + "/{letter}";

    @Autowired
    @Lazy(true)
    Store store;

    @RequestMapping(method = RequestMethod.POST, headers = ACCEPT_JSON_UTF_8)
    public void add(@RequestBody Record record) {
        store.add(record);
    }

    @RequestMapping(path = GET_BY_NAME, method = RequestMethod.GET)
    public Record getByName(@PathVariable("name") String name) {
        return store.getByName(name);
    }

    @RequestMapping(path = GET_BY_FIRST_LETTER, method = RequestMethod.GET)
    public List<Record> getByFirstLetter(@PathVariable("letter") char letter) {
        return store.getByLetter(letter);
    }

}

