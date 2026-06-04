package com.IdentityReconciliationBiteSpeed.Solution.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContactDto {
    private long primaryContactId;
    private List<String> emails;
    private List<String> phoneNumbers;
    private List<Integer> secondaryContactIds;

    public ContactDto(){}

    public ContactDto(long primaryContactId, List<String> emails, List<String> phoneNumbers, List<Integer> secondaryContactIds) {
        this.primaryContactId = primaryContactId;
        this.emails = emails;
        this.phoneNumbers = phoneNumbers;
        this.secondaryContactIds = secondaryContactIds;
    }
}
