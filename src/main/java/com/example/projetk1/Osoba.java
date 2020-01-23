package com.example.projetk1;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

import javax.validation.constraints.*;

import org.springframework.stereotype.Component;
import com.opencsv.bean.*;

@Component("osoba")
@Data
public class Osoba {

    @CsvBindByName(column = "id")
    private String id;

    @CsvBindByName(column = "first_name")
    @NotEmpty(message = "Proszę podać imię")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Imię musi zaczynać się od wielkiej litery")
    private String imie;

    @CsvBindByName(column = "last_name")
    @NotEmpty(message = "Proszę podać nazwisko")
    @Pattern(regexp = "[A-Z][a-z]*", message = "Nazwisko musi zaczynać się od wielkiej litery")
    @Size(max = 30, message = "Nazwisko musi być nie dłuższe niż 30 znaków")
    private String nazwisko;

    @CsvBindByName(column = "email")
    @NotEmpty(message = "Proszę podać email")
    private String email;

    @CsvBindByName(column = "gender")
    @NotEmpty(message = "Proszę podać płeć")
    private String plec;

    @CsvBindByName(column = "credit card type")
    @NotEmpty(message = "Proszę podać typ karty kredytowej")
    @Size(min = 2, message = "Typ karty kredytowej musi zawierać przynajmniej 2 znaki")
    private String typKartyKredytowej;

    @CsvBindByName(column = "credit card number")
    @NotEmpty(message = "Proszę podać numer karty kredytowej")
    @Pattern(regexp = "[0-9]*", message = "Numer musi być podany tylko z cyfr")
    private String numerKartyKredytowej;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlec() {
        return plec;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    public String getTypKartyKredytowej() {
        return typKartyKredytowej;
    }

    public void setTypKartyKredytowej(String typKartyKredytowej) {
        this.typKartyKredytowej = typKartyKredytowej;
    }

    public String getNumerKartyKredytowej() {
        return numerKartyKredytowej;
    }

    public void setNumerKartyKredytowej(String numerKartyKredytowej) {
        this.numerKartyKredytowej = numerKartyKredytowej;
    }
}
