package com.collegesearch.collegesearch.repository;

import com.collegesearch.collegesearch.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
List<UserEntity>findByEmail(String Email);
}
