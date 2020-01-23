package com.example.projetk1.Service;
import com.example.projetk1.Osoba;
import java.util.List;

public interface OsobaManager {

        void addPerson(Osoba osoba);
        Osoba findById(String id);
        List<Osoba> getAllPersons();
        void remove(String id);
        void replacePerson(Osoba osoba);
        List<Osoba> findByEmail(Osoba osoba);
        void importCsvFile();
        void exportCsvFile();
    }

