package me.kutan.korn.blog.service;

import static me.kutan.korn.blog.common.Constants.*;

import java.util.Date;
import java.util.List;

import org.pegdown.PegDownProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import me.kutan.korn.blog.model.Entry;
import me.kutan.korn.blog.repository.EntryRepository;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/13/2015
 */

@Service("entryService")
@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {DataAccessException.class, Exception.class})
public class EntryServiceImpl implements EntryService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private EntryRepository entryRepository;

    @Override
    public void saveEntry(Entry entry) {

        /*<!-- Checking: Is published status. -->*/
        boolean isPublished = entry.isPublished();
        if (isPublished) {
            entry.setPublishedDate(new Date());
        }

        /*<!-- Markdown Processing -->*/
        PegDownProcessor processor = new PegDownProcessor();
        String html = processor.markdownToHtml(entry.getMarkdown());
        entry.setBody(html);

        /*<!-- Persisting: Entry. -->*/
        entry.setUpdatedDate(new Date());

        entryRepository.save(entry);
    }

    @Override
    @Transactional(readOnly = true)
    public Entry findEntryBySlug(String slug) {

        return entryRepository.findBySlug(slug);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findDraftEntries() {

        return entryRepository.findDraftEntries();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findArchiveEntries() {

        return entryRepository.findArchiveEntries();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findCodingTypeEntries() {

        return entryRepository.findByType(CODING_TYPE);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Entry> findBlogTypeEntries() {

        return entryRepository.findByType(BLOG_ENTRY_TYPE);
    }
}
