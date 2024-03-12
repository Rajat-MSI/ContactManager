package com.project.customerrelationshipmanager.repository;

import com.project.customerrelationshipmanager.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>
{
    //method for pagination
    @Query("from Contact as c where c.user.userId = :userId")
    List<Contact> findContactByUserId(@Param("userId") int userId);
}
