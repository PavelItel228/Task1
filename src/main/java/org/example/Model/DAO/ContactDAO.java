package org.example.Model.DAO;

import org.example.Model.Entity.Contact;
import org.example.Model.Entity.PersonalInfo.Email;
import org.example.Model.Entity.PersonalInfo.PersonalInformation;
import org.example.Model.Entity.PersonalInfo.PhoneNumber;
import org.example.Model.Entity.PersonalInfo.SkypeLogin;

import java.util.*;

public class ContactDAO implements DAO<Contact> {

    private List<Contact> contacts = new ArrayList<>();

    public ContactDAO() {
        List<PhoneNumber> phones = new ArrayList<>();
        phones.add(new PhoneNumber("+38012345678"));
        contacts.add(Contact.builder().id(0).firstName("Саша")
                .lastName("Иванов")
                .phones(phones)
                .emails(new ArrayList<Email>())
                .skypes(new ArrayList<SkypeLogin>())
                .build());
        contacts.add(Contact.builder().id(1).firstName("Ваня").lastName("Петров")
                .emails(new ArrayList<Email>())
                .phones(new ArrayList<PhoneNumber>())
                .skypes(new ArrayList<SkypeLogin>())
                .build());
        contacts.add(Contact.builder().id(2).firstName("Вова").lastName("Орлов")
                .emails(new ArrayList<Email>())
                .phones(new ArrayList<PhoneNumber>())
                .skypes(new ArrayList<SkypeLogin>())
                .build());
    }

    @Override
    public Optional<Contact> getById(long id) {
        return Optional.ofNullable(contacts.get((int) id));
    }

    @Override
    public List<Contact> getAll() {
        return contacts;
    }

    @Override
    public void update(Contact contact, String[] args) {

    }

    @Override
    public void save(Contact contact) {
        contacts.add(contact);
    }
    public void updatePhones(Contact contact, PhoneNumber phoneNumber) {
        if (this.getById(contact.getId()).isPresent()) {
            contact.getPhones().add(phoneNumber);
        }
    }


    public void updateEmails(Contact contact, Email email) {
        if (this.getById(contact.getId()).isPresent()) {
            contact.getEmails().add(email);
        }
    }

    public void updateSkypes(Contact contact, SkypeLogin skypeLogin) {
        if (this.getById(contact.getId()).isPresent()) {
            contact.getSkypes().add(skypeLogin);
        }
    }

    @Override
    public void delete(Contact contact) {
        contacts.remove(contact);
    }

    public Contact getByFirstNameAndLastName(String firstName, String lastName) {
        return contacts.stream().filter(contact -> contact.getFirstName().equals(firstName) &&
                contact.getLastName().equals(lastName)).findFirst().orElse(null);
    }

    public List<Contact> getAllSortedByName() {
        contacts.sort(new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getFirstName().compareTo(contact2.getFirstName());
            }
        });
        return contacts;
    }
}
