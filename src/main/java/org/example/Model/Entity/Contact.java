package org.example.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.example.Model.Entity.PersonalInfo.Email;
import org.example.Model.Entity.PersonalInfo.PhoneNumber;
import org.example.Model.Entity.PersonalInfo.SkypeLogin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class Contact {
    private long id;
    private String firstName;
    private String lastName;
    private List<PhoneNumber> phones;
    private List<SkypeLogin> skypes;
    private List<Email> emails;

}
