package com.example.springdataexcercise;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("Select u from User u")
    List<User> findAllJPQL();


    @Query(value = "select * from User", nativeQuery = true)
    List<User> findAllSQL();

    List<User> findAllByLastNameContains(String letter);

    @Query(value = "select u from User u where u.firstName like '%o%' or u.lastName like '%o%'")
    List<User> findWithoLetterJPQL();

    @Query(value = "select * from User where first_name like '%o%' or last_name like '%o%'", nativeQuery = true)
    List<User> findWithoLetterSQL();

    void deleteByFirstNameStartingWith(String letter);

    @Modifying
    @Transactional
    @Query(value = "delete from User u where u.firstName like 'K%'", nativeQuery = true)
    void deleteBynameJPQL();


    @Modifying
    @Transactional
    @Query(value = "delete from User where first_name like 'K%'", nativeQuery = true)
    void deleteBynameSQL();


       List<User>findAllByOrderByLastNameAsc();


    @Query(value = "select u from User u order by last_name asc")
    List<User> findAllOrderByLastNameAscJPQL();

    @Query(value = "select * from User order by last_name asc", nativeQuery = true)
    List<User> findAllOrderByLastNameAscSQL();


}
