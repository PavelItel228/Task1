package org.example;

import org.example.Controller.Controller;
import org.example.Model.DAO.ContactDAO;
import org.example.Model.service.ContactService;
import org.example.View.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View(), new ContactService(new ContactDAO()));
        controller.processContacts();
    }
}
