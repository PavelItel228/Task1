package org.example.Controller;

import org.example.Model.Entity.Contact;
import org.example.Model.Entity.PersonalInfo.Email;
import org.example.Model.Entity.PersonalInfo.PhoneNumber;
import org.example.Model.Entity.PersonalInfo.SkypeLogin;
import org.example.Model.service.ContactService;
import org.example.Model.service.PersonalInformationService;
import org.example.View.View;

import java.util.ArrayList;

import java.util.Scanner;

public class Controller {
    private View view;
    private final ContactService contactService;
    private final PersonalInformationService personalInformationService = new PersonalInformationService();

    public Controller(View view, ContactService contactService) {
        this.view = view;
        this.contactService = contactService;
    }

    public void processContacts() {
        Scanner scan = new Scanner(System.in);
        view = setLocale(scan);
        mainMenu(scan);
    }

    private View setLocale(Scanner scan) {
        String res;
        view.printLocales();
        while (!(scan.hasNext() && ((res = scan.next()).equals(View.ONE) || res.equals(View.TWO)))) {
            view.printMessage(view.WRONG_INPUT);
            view.printLocales();
        }
        return view.setLocale(res);
    }

    private void mainMenu(Scanner scan) {
        String res;
        while (true) {
            view.printMenu();
            while (!(scan.hasNext() && ((res = scan.next()).equals(View.ONE) || res.equals(View.TWO) || res.equals(View.THREE)))) {
                view.printMessage(view.WRONG_INPUT);
                view.printMenu();
            }
            switch (res) {
                //todo make with view
                case View.ONE:
                    createContact(scan);
                    break;
                case View.TWO:
                    showContacts(scan);
                    break;
                case View.THREE:
                    findContact(scan);
                    break;
            }
        }
    }

    private void createContact(Scanner scan) {
        String res;
        String firstName;
        String lastName;
        ArrayList<SkypeLogin> skypes = new ArrayList<>();
        ArrayList<PhoneNumber> phones = new ArrayList<>();
        ArrayList<Email> emails = new ArrayList<>();
        Contact.ContactBuilder newContact = Contact.builder();
        view.printMessage(view.INPUT_FIRST_NAME);
        firstName = scan.next();
        view.printMessage(view.INPUT_LAST_NAME);
        lastName = scan.next();
        newContact.id(contactService.getAllContacts().size() - 1).firstName(firstName).lastName(lastName);
        while (true) {
            view.printAddMenu();
            while (!(scan.hasNext() && ((res = scan.next()).equals(View.ONE)
                    || res.equals(View.TWO)
                    || res.equals(View.THREE)
                    || res.equals(View.FOUR)
            ))) {
                view.printMessage(view.WRONG_INPUT);
                view.printAddMenu();
            }
            switch (res) {
                case View.ONE:
                    view.printMessage(view.INPUT_PHONE);
                    while (scan.hasNext()) {
                        res = scan.next();
                        if (personalInformationService.isValid(res, view.PHONE_REGEXP)) {
                            phones.add(new PhoneNumber(res));
                            break;
                        } else {
                            view.printMessage(view.WRONG_INPUT);
                        }
                    }
                    break;

                case View.TWO:
                    view.printMessage(view.INPUT_EMAIL);
                    while (scan.hasNext()) {
                        res = scan.next();
                        if (personalInformationService.isValid(res, view.EMAIL_REGEXP)) {
                            emails.add(new Email(res));
                            break;
                        } else {
                            view.printMessage(view.WRONG_INPUT);
                        }
                    }
                    break;

                case View.THREE:
                    view.printMessage(view.INPUT_SKYPE);
                    while (scan.hasNext()) {
                        res = scan.next();
                        if (personalInformationService.isValid(res, view.SKYPE_REGEXP)) {
                            skypes.add(new SkypeLogin(res));
                            break;
                        } else {
                            view.printMessage(view.WRONG_INPUT);
                        }
                    }
                    break;
            }

            if (res.equals(View.FOUR)) {
                newContact.phones(phones).skypes(skypes).emails(emails);
                Contact contact = newContact.build();
                if (contactService.isExists(contact)){
                    view.printMessage(view.MERGE);
                    view.printYesOrNo();
                    while (!(scan.hasNext() && ((res = scan.next()).equals(View.ONE) || res.equals(View.TWO)))) {
                        view.printMessage(view.WRONG_INPUT);
                        view.printYesOrNo();
                    }
                    if (res.equals(View.ONE)){
                        contactService.mergeContacts(contact);
                    }
                }
                contactService.saveContact(contact);
                break;
            }
        }
    }

    private void findContact(Scanner scan) {
        String firstName;
        String lastName;
        Contact.ContactBuilder newContact = Contact.builder();
        view.printMessage(view.INPUT_FIRST_NAME);
        firstName = scan.next();
        view.printMessage(view.INPUT_LAST_NAME);
        lastName = scan.next();
        newContact.firstName(firstName).lastName(lastName);;
        Contact contact = contactService.getContactByFullName(firstName, lastName);
        if(contact != null) {
            view.printUsers(new ArrayList<Contact>() {{
                add(contact);
            }});
        }else {
            view.printMessage(view.NOT_FOUND);
        }
    }

    private void showContacts(Scanner scan) {
        String res;
        view.printUsers(contactService.getAllContacts());
        while (true) {
            view.printShowMenu();
            while (!(scan.hasNext() && ((res = scan.next()).equals(View.ONE) || res.equals(View.TWO)))) {
                view.printMessage(view.WRONG_INPUT);
                view.printShowMenu();
            }
            if (res.equals(View.TWO)) {
                break;
            }
            view.printUsers(contactService.getAllContactsSorted());
        }
    }

}


