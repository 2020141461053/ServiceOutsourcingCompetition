package com.spring.DAO;


import com.spring.entity.Files;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FilesDAO extends JpaRepository<Files,Integer> {

  Files findById(int id);


}
