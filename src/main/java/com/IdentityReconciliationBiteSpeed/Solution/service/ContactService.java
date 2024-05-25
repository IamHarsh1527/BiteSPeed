package com.IdentityReconciliationBiteSpeed.Solution.service;

import com.IdentityReconciliationBiteSpeed.Solution.Dto.ContactDto;
import com.IdentityReconciliationBiteSpeed.Solution.Dto.RequestDto;
import com.IdentityReconciliationBiteSpeed.Solution.Dto.ResponseDto;
import com.IdentityReconciliationBiteSpeed.Solution.models.Contact;
import com.IdentityReconciliationBiteSpeed.Solution.models.LinkedPrecedence;
import com.IdentityReconciliationBiteSpeed.Solution.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContactService {

    private ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public ResponseDto getTheInformationforUser(RequestDto requestDto) {

        List<Contact> contactsByEmail = null;
        if(requestDto.getEmail()!=null){
            contactsByEmail = getByEmailAndPrimary(requestDto.getEmail());
        }
        List<Contact> contactsByPhone=null;
        if(requestDto.getPhoneNumber()!=null){
            contactsByPhone = getByPhoneNumber(requestDto.getPhoneNumber());
        }
        Set<Contact> res = new HashSet<>();
        if (contactsByEmail != null) {
            res.addAll(contactsByEmail);
        }
        if (contactsByPhone != null) {
            res.addAll(contactsByPhone);
        }
        List<Contact> finalContact =new ArrayList<>(res.size());
        List<String> Emails =new ArrayList<>();
        List<String> PhoneNum =new ArrayList<>();
        finalContact.addAll(res);
        Contact primaryContact= null;
        for(int i=0;i<finalContact.size();i++){
            if(finalContact.get(i).getLinkedPrecedence().equals(LinkedPrecedence.primary)) {
                primaryContact = finalContact.get(i);
            }
        }
        Emails.add(primaryContact.getEmail());
        PhoneNum.add(primaryContact.getPhoneNumber());
        List<Integer> secondaryId=new ArrayList<>();
        ContactDto contactDto = new ContactDto();
        contactDto.setPrimaryContactId(primaryContact.getId());
        for(int i=0;i<finalContact.size();i++){
            if(finalContact.get(i).getLinkedPrecedence().equals(LinkedPrecedence.secondary)){
                secondaryId.add(finalContact.get(i).getId());
                Emails.add(finalContact.get(i).getEmail());
                if(!PhoneNum.contains(finalContact.get(i).getPhoneNumber())){
                    PhoneNum.add(finalContact.get(i).getPhoneNumber());
                }
            }

        }
        contactDto.setSecondaryContactIds(secondaryId);
        contactDto.setEmails(Emails);
        contactDto.setPhoneNumbers(PhoneNum);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setContactDto(contactDto);
        return responseDto;
    }

    private List<Contact> getByPhoneNumber(String phoneNumber) {
        Optional<List<Contact>> ByPhone = contactRepository.findByphoneNumber(phoneNumber);
        if(ByPhone.isEmpty()){
            return null;
        }
        return ByPhone.get();
    }

    private List<Contact> getByEmailAndPrimary(String email) {
        List<Contact> ans =null;
        Optional<List<Contact>> byEmail = contactRepository.findByemail(email);
        if (byEmail.isEmpty()){
            return null;
        }
        List<Contact> contacts = byEmail.get();
        List<Contact> contactsByPrimary = null;
        for(int i=0;i<contacts.size();i++){
            Contact contact = contacts.get(i);
            //if(contact.getLinkedPrecedence().equals(LinkedPrecedence.primary)){
                String number = contact.getPhoneNumber();
                 contactsByPrimary = getByPhoneNumber(number);
            //}
        }
        Set<Contact> hs = new HashSet<>();
        hs.addAll(contacts);
        if (contactsByPrimary != null) {
            hs.addAll(contactsByPrimary);
        }
        ans=new ArrayList<>(hs);
        return ans;
    }
}
