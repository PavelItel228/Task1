package org.example.Model.service;


public class PersonalInformationService {

    public boolean isValid(String info, String regexp) {
        return info.matches(regexp);
    }

}
