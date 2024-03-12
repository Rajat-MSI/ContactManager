package com.project.customerrelationshipmanager.repository;

import com.project.customerrelationshipmanager.model.Contact;
import com.project.customerrelationshipmanager.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>
{
    //method for pagination
    @Query("from Contact as c where c.user.userId = :userId")
    Page<Contact> findContactByUserId(@Param("userId") int userId, Pageable pageable);

    @Query("select c from Contact c where c.contactId = :contactId and c.user.userId = :userId")
    Optional<Contact> findContactByUserId(@Param("contactId") int contactId, @Param("userId") int userId);

    @Query("select case when (count(c) > 0) then true else false end from Contact c where c.user.userId = :userId and c.contactId = :contactId" )
    boolean isContactExistByUserId(@Param("contactId") int contactId, @Param("userId") int userId);
}
