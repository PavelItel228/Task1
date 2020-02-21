package org.example.Model.Entity.PersonalInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersonalInformation {

    private final String INFORMATION;

    protected PersonalInformation(String information) {
        INFORMATION = information;
    }
}
