package com.ericarfs.spring_boot_mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ericarfs.spring_boot_mongodb.entity.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
