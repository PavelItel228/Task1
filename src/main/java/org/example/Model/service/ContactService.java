package org.example.Model.service;

import org.example.Model.DAO.ContactDAO;
import org.example.Model.Entity.Contact;
import org.example.Model.Entity.PersonalInfo.Email;
import org.example.Model.Entity.PersonalInfo.PhoneNumber;
import org.example.Model.Entity.PersonalInfo.SkypeLogin;

import java.util.List;

public class ContactService {
    ContactDAO contactDAO;

    public ContactService(ContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public void saveContact(Contact contact) {
        contactDAO.save(contact);
    }

    public void mergeContacts(Contact contact) {
        Contact contactToMerge = contactDAO.getByFirstNameAndLastName(contact.getFirstName(),
                contact.getLastName());
        List<PhoneNumber> phones = contactToMerge.getPhones();
        List<Email> emails = contactToMerge.getEmails();
        List<SkypeLogin> skypeLogins = contactToMerge.getSkypes();
        contact.getPhones().addAll(phones);
        contact.getEmails().addAll(emails);
        contact.getSkypes().addAll(skypeLogins);
        contactDAO.delete(contactToMerge);
    }

    public boolean isExists(Contact contact) {
        return contactDAO.getByFirstNameAndLastName(contact.getFirstName(), contact.getLastName()) != null;
    }

    public Contact getContactByFullName(String firstName, String lastName) {
        return contactDAO.getByFirstNameAndLastName(firstName, lastName);
    }

    public List<Contact> getAllContacts() {
        return contactDAO.getAll();
    }

    public List<Contact> getAllContactsSorted() {
        return contactDAO.getAllSortedByName();
    }

}
