package com.IdentityReconciliationBiteSpeed.Solution.repository;

import com.IdentityReconciliationBiteSpeed.Solution.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {


    Optional<List<Contact>> findByemail(String email);

    Optional<List<Contact>> findByphoneNumber(String phoneNumber);
}
