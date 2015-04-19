package me.kutan.korn.blog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import me.kutan.korn.blog.model.User;

/**
 * Project: korn-blog
 * Author: Korn Kutan <korn@kutan.me>
 * Date: 4/11/2015
 */

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
