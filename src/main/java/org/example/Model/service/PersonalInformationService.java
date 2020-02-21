package org.example.Model.service;

import org.example.Model.DAO.ContactDAO;
import org.example.Model.Entity.Contact;
import org.example.Model.Entity.PersonalInfo.PersonalInformation;

public class PersonalInformationService {

    public boolean isValid(String info,String regexp) {
        return info.matches(regexp);
    }

}
