package com.lessons.db.phonebook.tests;

import com.lessons.db.phonebook.db.Store;
import com.lessons.db.phonebook.model.Record;
import com.lessons.db.phonebook.rest.PhonebookController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test only availability of REST controller
 * without real call of DB services
 */
@RunWith(SpringRunner.class)
@WebMvcTest(PhonebookController.class)
public class RestAvailabilityTest {

    private static final String name = "ИмяДляТеста";
    private static final String phone = "111-22-33";
    private static final String jsonRescord = String.format("{\"name\":\"%s\",\"phone\":\"%s\"}", name, phone);
    private static final String jsonRecordsArray = "[" + jsonRescord + "]";
    private static final String incorrectJson = "%=[{(!!";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private Store store;

    @Test
    public void testAdd() throws Exception {
        willDoNothing().given(this.store).add(any(Record.class));
        this.mvc
                .perform(
                        post(PhonebookController.ROOT_PATH)
                                .content(jsonRescord)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk());
    }

    @Test
    public void testIncorrectAdd() throws Exception {
        willDoNothing().given(this.store).add(any(Record.class));
        this.mvc
                .perform(
                        post(PhonebookController.ROOT_PATH)
                                .content(incorrectJson)
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().is(400));
    }

    @Test
    public void testGetByName() throws Exception {
        given(this.store.getByName(anyString()))
                .willReturn(new Record(name, phone));
        this.mvc
                .perform(
                        get(PhonebookController.ROOT_PATH + PhonebookController.GET_BY_NAME_PATH + "/" + name)
                                .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRescord));
    }

    @Test
    public void testGetByFirstLetter() throws Exception {
        given(this.store.getByLetter(anyChar()))
                .willReturn(Collections.singletonList(new Record(name, phone)));
        this.mvc
                .perform(
                        get(PhonebookController.ROOT_PATH + PhonebookController.GET_BY_FIRST_LETTER_PATH + "/Я")
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andExpect(content().json(jsonRecordsArray));
    }

}