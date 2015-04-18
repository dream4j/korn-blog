package me.kutan.korn.blog.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static me.kutan.korn.blog.common.Constants.*;

import me.kutan.korn.blog.model.Entry;
import me.kutan.korn.blog.model.User;
import me.kutan.korn.blog.repository.EntryRepository;
import me.kutan.korn.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Controller
public class SettingController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private UserRepository userRepository;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private EntryRepository entryRepository;


    @RequestMapping(value = "/settings", method = GET)
    public String settings(ModelMap map) {

        map.put(PAGE_ATTRIBUTE, SETTINGS_TEMPLATE);
        return SETTINGS_TEMPLATE;
    }


    @ResponseBody
    @RequestMapping("/create")
    public String create(String userId, String password, String firstname, String lastname) {
        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(password);

            User user = new User(userId, hashedPassword, firstname, lastname);
            userRepository.save(user);

        }  catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }

        return "User succesfully created!";
    }

    @ResponseBody
    @RequestMapping("/find")
    public String find() {

        String message = null;
        try {
            Entry entry = entryRepository.findBySlug("test-title");
            message = "User Search Result: " +  entry.toString();

        }  catch (Exception ex) {
            return "Error finding the user: " + ex.toString();
        }

        return message;
    }
}
