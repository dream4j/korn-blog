package me.kutan.korn.blog.service;

import me.kutan.korn.blog.model.Entry;

import java.util.List;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/13/2015
 */

public interface EntryService {

    void saveEntry(Entry entry);

    Entry findEntryBySlug(String slug);

    List<Entry> findDraftEntries();

    List<Entry> findArchiveEntries();

    List<Entry> findCodingTypeEntries();

    List<Entry> findBlogTypeEntries();
}
