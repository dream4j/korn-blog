package me.kutan.korn.blog.repository;

import me.kutan.korn.blog.model.Entry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Transactional
public interface EntryRepository extends CrudRepository<Entry, Integer> {

    Entry findBySlug(String slug);

    @Query("SELECT e FROM Entry e WHERE e.published = TRUE AND e.type = :type")
    List<Entry> findByType(@Param("type") String type);

    @Query("SELECT e FROM Entry e WHERE e.published = FALSE")
    List<Entry> findDraftEntries();

    @Query("SELECT e.title, e.slug, e.publishedDate FROM Entry e WHERE e.published = TRUE")
    List<Entry> findArchiveEntries();
}
