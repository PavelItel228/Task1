package org.example.View;

import org.example.Model.Entity.Contact;
import org.example.Model.Entity.PersonalInfo.Email;
import org.example.Model.Entity.PersonalInfo.PhoneNumber;
import org.example.Model.Entity.PersonalInfo.SkypeLogin;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class View {
    public final String WRONG_INPUT;
    public final String RUSSIAN;
    public final String ENGLISH;
    public final String SELECT_LOCALE;
    public final String CREATE_CONTACT;
    public final String SHOW_ALL;
    public final String FIND_BY_NAME;
    public final String BACK;
    public final String SORT_BY_NAME;
    public final String INPUT_FIRST_NAME;
    public final String INPUT_LAST_NAME;
    public final String ADD_PHONE;
    public final String ADD_EMAIL;
    public final String ADD_SKYPE;
    public final String SAVE;
    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";
    public static final String FOUR = "4";
    private final ResourceBundle BUNDLE = ResourceBundle.getBundle("messages");
    public final String PHONE_REGEXP;
    public final String EMAIL_REGEXP;
    public final String SKYPE_REGEXP;
    public final String MERGE;
    public final String YES;
    public final String NO;
    private final String PHONES;
    private final String EMAILS;
    private final String SKYPES;
    public final String NOT_FOUND;
    public final String INPUT_PHONE;
    public final String INPUT_EMAIL;
    public final String INPUT_SKYPE;

    public View() {
        RUSSIAN = readUnicodeFromBundle("locale.russian");
        ENGLISH = readUnicodeFromBundle("locale.english");
        SELECT_LOCALE = readUnicodeFromBundle("select.locale");
        WRONG_INPUT = readUnicodeFromBundle("wrong.input");
        CREATE_CONTACT = readUnicodeFromBundle("add.contact");
        SHOW_ALL = readUnicodeFromBundle("show.all.contacts");
        FIND_BY_NAME = readUnicodeFromBundle("find.contact.by.name");
        SORT_BY_NAME = readUnicodeFromBundle("menu.sort");
        BACK = readUnicodeFromBundle("menu.back");
        INPUT_FIRST_NAME = readUnicodeFromBundle("input.first.name");
        INPUT_LAST_NAME = readUnicodeFromBundle("input.last.name");
        ADD_PHONE = readUnicodeFromBundle("add.phone");
        ADD_EMAIL = readUnicodeFromBundle("add.email");
        ADD_SKYPE = readUnicodeFromBundle("add.skype");
        SAVE = readUnicodeFromBundle("save");
        PHONE_REGEXP = readUnicodeFromBundle("regexp.phone");
        EMAIL_REGEXP = readUnicodeFromBundle("regexp.email");
        SKYPE_REGEXP = readUnicodeFromBundle("regexp.login");
        MERGE = readUnicodeFromBundle("merge");
        YES = readUnicodeFromBundle("yes");
        NO = readUnicodeFromBundle("no");
        PHONES = readUnicodeFromBundle("phones");
        EMAILS = readUnicodeFromBundle("emails");
        SKYPES = readUnicodeFromBundle("skypes");
        NOT_FOUND = readUnicodeFromBundle("contact.not.found");
        INPUT_PHONE = readUnicodeFromBundle("input.phone");
        INPUT_EMAIL = readUnicodeFromBundle("input.email");
        INPUT_SKYPE = readUnicodeFromBundle("input.skype");
    }

    private String readUnicodeFromBundle(String value) {
        return new String(BUNDLE.getString(value).getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8);
    }

    public void printMessages(String... args) {
        for (String arg : args) {
            System.out.print(String.format("%s ", arg));
        }
    }

    public void printYesOrNo() {
        System.out.println(String.format("1. %s", YES));
        System.out.println(String.format("2. %s", NO));
    }

    public void printMessage(String arg) {
        System.out.println(arg);
    }

    public void printShowMenu() {
        System.out.println(String.format("1. %s", SORT_BY_NAME));
        System.out.println(String.format("2. %s", BACK));
    }

    public void printLocales() {
        System.out.println(String.format("%s", SELECT_LOCALE));
        System.out.println(String.format("1. %s", ENGLISH));
        System.out.println(String.format("2. %s", RUSSIAN));
    }

    //todo unite all menu's in more abstract method printMenu(String... arg)
    public void printAddMenu() {
        System.out.println(String.format("1. %s", ADD_PHONE));
        System.out.println(String.format("2. %s", ADD_EMAIL));
        System.out.println(String.format("3. %s", ADD_SKYPE));
        System.out.println(String.format("4. %s", SAVE));

    }

    public void printUsers(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.print("\n" + contact.getFirstName() + " " + contact.getLastName());
            if (!contact.getSkypes().isEmpty()) {
                System.out.print("\n" +SKYPES + ": ");
                for (SkypeLogin skype : contact.getSkypes()) {
                    System.out.print(skype.getINFORMATION() + " ");
                }
            }
            if (!contact.getPhones().isEmpty()) {
                System.out.print("\n" +PHONES + ": ");
                for (PhoneNumber phone : contact.getPhones()) {
                    System.out.print(phone.getINFORMATION() + " ");
                }
            }
            if (!contact.getEmails().isEmpty()) {
                System.out.print("\n" +EMAILS + ": ");
                for (Email email : contact.getEmails()) {
                    System.out.print(email.getINFORMATION() + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public View setLocale(String localeCode) {
        if (localeCode.equals("1")) {
            Locale.setDefault(new Locale("en"));
        } else if (localeCode.equals("2")) {
            Locale.setDefault(new Locale("ru"));
        }
        //TODO find better solution
        return new View();
    }

    public void printMenu() {
        System.out.println(String.format("1. %s", CREATE_CONTACT));
        System.out.println(String.format("2. %s", SHOW_ALL));
        System.out.println(String.format("3. %s", FIND_BY_NAME));
    }
}
