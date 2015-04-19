package me.kutan.korn.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Data
@Entity
@Table(name = "ENTRIES")
@EqualsAndHashCode(callSuper = false)
public class Entry extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "slug", nullable = false, unique = true)
    private String slug;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "markdown", columnDefinition = "TEXT")
    private String markdown;

    @Column(name = "type")
    private String type;

    @Column(name = "is_published")
    private boolean published;

    @Column(name = "published_date")
    private Date publishedDate;

    public Entry() {
    }

    public Entry(String title, String slug, Date updatedDate) {
        this.title = title;
        this.slug  = slug;
        setUpdatedDate(updatedDate);
    }

    public Entry(String title, String slug, Date updatedDate, Date publishedDate) {
        this.title = title;
        this.slug  = slug;
        this.publishedDate = publishedDate;
        setUpdatedDate(updatedDate);
    }
}
