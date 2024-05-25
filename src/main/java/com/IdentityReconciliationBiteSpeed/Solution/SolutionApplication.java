package com.IdentityReconciliationBiteSpeed.Solution;

import com.IdentityReconciliationBiteSpeed.Solution.models.Contact;
import com.IdentityReconciliationBiteSpeed.Solution.models.LinkedPrecedence;
import com.IdentityReconciliationBiteSpeed.Solution.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SolutionApplication implements CommandLineRunner{
	@Autowired
	private ContactRepository contactRepository;
	public static void main(String[] args) {
		SpringApplication.run(SolutionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Contact contact = new Contact();
//		contact.setId(2);
//		contact.setEmail("lorraine@hillvalley.edu");
//		contact.setPhoneNumber("123456");
//		contact.setLinkedPrecedence(LinkedPrecedence.primary);
//		contactRepository.save(contact);
//		Contact contact2 = new Contact();
//		contact2.setId(23);
//		contact2.setEmail("mcfly@hillvalley.edu");
//		contact2.setPhoneNumber("123456");
//		contact2.setLinkedId(1);
//		contact2.setLinkedPrecedence(LinkedPrecedence.secondary);
//		contactRepository.save(contact2);

	}
}
