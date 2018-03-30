package com.gitsome.chan.repository;

import com.gitsome.chan.entity.Dict;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * @author : 130801(cgs)
 * Date : 2018-01-29
 * Time : 10:44
 */
public interface DictRepository extends JpaRepository<Dict,UUID>{
}