package com.company.SecurityApp.SecurityApplication.repositories;

import com.company.SecurityApp.SecurityApplication.entities.PostEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<PostEntity,Long> {
}


/**
 * @Repository
 * public Interface PostRepository extends JpaRepository<PostEntity,long> {
 *
 */