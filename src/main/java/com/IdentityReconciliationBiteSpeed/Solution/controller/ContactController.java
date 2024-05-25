package com.IdentityReconciliationBiteSpeed.Solution.controller;

import com.IdentityReconciliationBiteSpeed.Solution.Dto.RequestDto;
import com.IdentityReconciliationBiteSpeed.Solution.Dto.ResponseDto;
import com.IdentityReconciliationBiteSpeed.Solution.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/identity")
    public ResponseDto GetInformation(@RequestBody RequestDto requestDto) {
        if(requestDto.getPhoneNumber()==null && requestDto.getEmail()==null){
            return null;
        }
        return contactService.getTheInformationforUser(requestDto);
    }

}
