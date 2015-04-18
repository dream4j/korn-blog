package me.kutan.korn.blog.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/13/2015
 */

@Data
@Entity
@Table(name = "JOURNALS")
@EqualsAndHashCode(callSuper = false)
public class Journal extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;
}
