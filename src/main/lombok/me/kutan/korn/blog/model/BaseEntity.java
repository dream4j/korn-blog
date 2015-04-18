package me.kutan.korn.blog.model;

import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class BaseEntity {

    @LastModifiedDate
    @Column(name = "updated_date")
    private Date updatedDate;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;
}
