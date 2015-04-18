package me.kutan.korn.blog.web.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;
import static me.kutan.korn.blog.common.Constants.*;

import me.kutan.korn.blog.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import me.kutan.korn.blog.model.Entry;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Controller
public class EntryController {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private EntryService entryService;

    private List<String> types = new ArrayList<>();
    {
        types.add(BLOG_ENTRY_TYPE);
        types.add(CODING_TYPE);
    }

    @RequestMapping(value = "/entry/{slug}", method = GET)
    public ModelAndView entry(@PathVariable String slug) {

        /*<!-- Getting: Entry by Slug. -->*/
        Entry entry = entryService.findEntryBySlug(slug);
        ModelAndView model = new ModelAndView(ENTRY_TEMPLATE);
        model.addObject("entry", entry);

        return model;
    }

    @RequestMapping(value = "/compose", method = GET)
    public ModelAndView compose() {

        ModelAndView model = new ModelAndView(COMPOSE_TEMPLATE);
        model.addObject("entry", new Entry());
        model.addObject("types", types);

        return model;
    }

    @RequestMapping(value = "/compose/{slug}", method = GET)
    public ModelAndView compose(@PathVariable String slug) {

        /*<!-- Getting: Entry by Slug. -->*/
        Entry entry = entryService.findEntryBySlug(slug);
        ModelAndView model = new ModelAndView(COMPOSE_TEMPLATE);
        model.addObject("entry", entry);
        model.addObject("types", types);

        return model;
    }

    @RequestMapping(value = "/compose", method = POST)
    public String compose(@ModelAttribute Entry entry) {

        /*<!-- Checking: Is Published Post? -->*/
        boolean isPublished = entry.isPublished();
        entryService.saveEntry(entry);

        if (isPublished) return "redirect:/" + ENTRY_TEMPLATE + "/" + entry.getSlug();
        else             return "redirect:/" + DRAFT_TEMPLATE;
    }

    @RequestMapping(value = "/draft", method = GET)
    public ModelAndView draft() {

        /*<!-- Getting: All Draft Entry. -->*/
        List<Entry> draftEntries = entryService.findDraftEntries();
        ModelAndView model = new ModelAndView(DRAFT_TEMPLATE);
        model.addObject("draftEntries", draftEntries);

        return model;
    }
}