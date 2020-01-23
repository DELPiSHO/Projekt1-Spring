package com.example.projetk1.Service;

import com.example.projetk1.Osoba;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;
import com.opencsv.bean.*;
import java.util.ArrayList;
import com.opencsv.CSVWriter;
import java.util.List;
import java.util.UUID;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class OsobaService implements OsobaManager {

    private List<Osoba> osoby = new ArrayList<>();

    public void addPerson(Osoba osoba) {
        osoba.setId(UUID.randomUUID().toString());
        osoby.add(osoba);
    }

    @Override
    public Osoba findById(String id) {
        for (Osoba osoba: osoby) {
            if(osoba.getId().equals(id)){
                return osoba;
            }
        }
        return null;
    }

    @Override
    public List<Osoba> getAllPersons() {
        return osoby;
    }

    @Override
    public void remove(String id) {
        Osoba delete = null;
        for(Osoba osoba: osoby){
            if(osoba.getId().equals(id)){
                delete = osoba;
                break;
            }
        }
        if(delete != null)
            osoby.remove(delete);
    }

    @Override
    public void replacePerson(Osoba zmienicOsobe){
        for(Osoba osoba: osoby){
            if (osoba.getId().equals(zmienicOsobe.getId())){
                osoba.setImie(zmienicOsobe.getImie());
                osoba.setNazwisko(zmienicOsobe.getNazwisko());
                osoba.setEmail(zmienicOsobe.getEmail());
                osoba.setPlec(zmienicOsobe.getPlec());
                osoba.setTypKartyKredytowej(zmienicOsobe.getTypKartyKredytowej());
                osoba.setNumerKartyKredytowej(zmienicOsobe.getNumerKartyKredytowej());
                break;
            }
        }
    }

    @Override
    public List<Osoba> findByEmail(Osoba szukanaOsoba) {
        List<Osoba> wynik = new ArrayList<>();
        for(Osoba osoba: osoby){
            if(osoba.getEmail().equals(szukanaOsoba.getEmail())){
                wynik.add(osoba);
            }
        }
        return wynik;
    }

    @Override
    public void importCsvFile() {
        List<Osoba> csvOsoby = new ArrayList<>();
        String fileName = "./MOCK_DATA.csv";
        Path myPath = Paths.get(fileName);

        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8)) {

            HeaderColumnNameMappingStrategy<Osoba> strategy
                    = new HeaderColumnNameMappingStrategy<>();
            strategy.setType(Osoba.class);

            CsvToBean csvToBean = new CsvToBeanBuilder(br)
                    .withType(Osoba.class)
                    .withMappingStrategy(strategy)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            csvOsoby = csvToBean.parse();
            osoby.addAll(csvOsoby);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void exportCsvFile() {
        String csvExported = "./exported.csv";
        try (
                Writer writer = Files.newBufferedWriter(Paths.get(csvExported));
        ) {

            StatefulBeanToCsv<Osoba> beanToCsv = new StatefulBeanToCsvBuilder(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
            beanToCsv.write(osoby);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}

