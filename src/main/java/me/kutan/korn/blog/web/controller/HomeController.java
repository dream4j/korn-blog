package me.kutan.korn.blog.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static me.kutan.korn.blog.common.Constants.*;

import me.kutan.korn.blog.model.Entry;
import me.kutan.korn.blog.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Controller
public class HomeController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private EntryService entryService;

    @RequestMapping(value = "/", method = GET)
    public ModelAndView home(ModelMap map) {

        /*<!-- Getting: All Entries. -->*/
        List<Entry> entries = entryService.findBlogTypeEntries();

        /*<!-- Setting: Model. -->*/
        ModelAndView model = new ModelAndView(HOME_TEMPLATE);
        model.addObject("entries", entries);

        map.put(PAGE_ATTRIBUTE, HOME_TEMPLATE);
        return model;
    }

    @RequestMapping(value = "/journal", method = GET)
    public String journal(ModelMap map) {

        map.put(PAGE_ATTRIBUTE, JOURNAL_TEMPLATE);
        return JOURNAL_TEMPLATE;
    }

    @RequestMapping(value = "/code", method = GET)
    public ModelAndView code(ModelMap map) {

        /*<!-- Getting: All Coding Entries. -->*/
        List<Entry> entries = entryService.findCodingTypeEntries();

        /*<!-- Setting: Model. -->*/
        ModelAndView model = new ModelAndView(CODE_TEMPLATE);
        model.addObject("entries", entries);

        map.put(PAGE_ATTRIBUTE, CODE_TEMPLATE);
        return model;
    }

    @RequestMapping(value = "/archive", method = GET)
    public ModelAndView archive(ModelMap map) {

        /*<!-- Getting: All Archive Entries. -->*/
        List<Entry> entries = entryService.findArchiveEntries();

        /*<!-- Setting: Model. -->*/
        ModelAndView model = new ModelAndView(ARCHIVE_TEMPLATE);
        model.addObject("entries", entries);

        map.put(PAGE_ATTRIBUTE, ARCHIVE_TEMPLATE);
        return model;
    }

    @RequestMapping(value = "/about", method = GET)
    public String about(ModelMap map) {

        map.put(PAGE_ATTRIBUTE, ABOUT_TEMPLATE);
        return ABOUT_TEMPLATE;
    }

}
