package org.example.Model.DAO;

import org.example.Model.Entity.Contact;
import org.example.Model.Entity.PersonalInfo.PersonalInformation;
import org.example.Model.Entity.PersonalInfo.PhoneNumber;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> getById(long id);
    List<T> getAll();
    void update(T t, String[] args);
    void save(T t);
    void delete(T t);

}