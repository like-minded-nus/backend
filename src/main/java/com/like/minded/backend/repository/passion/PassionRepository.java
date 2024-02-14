package com.like.minded.backend.repository.passion;

import com.like.minded.backend.domain.passion.Passion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PassionRepository extends JpaRepository<Passion, Integer> {

}
