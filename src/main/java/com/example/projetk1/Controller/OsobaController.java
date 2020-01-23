package com.example.projetk1.Controller;


import com.example.projetk1.Osoba;
import com.example.projetk1.Service.OsobaManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.io.IOException;

@Controller("osobawepcontroller")
public class OsobaController {
    private OsobaManager om;

    @Autowired
    public OsobaController(OsobaManager om){
        this.om = om;
    }

    @GetMapping("listaOsob")
    public String home(Model model){
        model.addAttribute("osoby",om.getAllPersons());
        return "listaOsob";
    }

    @GetMapping("listaOsob/new")
    public String nowaOsoba(Model model){
        model.addAttribute("osoba",new Osoba());
        return "osobaForm";
    }

    @PostMapping("/listaOsob/add")
    public String dodacOsobe(@Valid @ModelAttribute("osoba") Osoba osoba, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "osobaForm";
        }
        om.addPerson(osoba);
        model.addAttribute("osoby",om.getAllPersons());
        return "listaOsob";
    }

    @GetMapping("/listaOsob/delete/{id}")
    public String usunacOsobe(@PathVariable("id") String id,Model model){
        om.remove(id);
        model.addAttribute("osoby",om.getAllPersons());
        return "listaOsob";
    }

    @GetMapping("/listaOsob/find")
    public String znalescOsobe(Model model){
        model.addAttribute("osoba",new Osoba());
        return "znalezcOsobe";
    }

    @PostMapping("listaOsob/filter")
    public String filter(Osoba osoba,Model model) {
        model.addAttribute("osoby",om.findByEmail(osoba));
        return "listaOsob";
    }

    @GetMapping("/listaOsob/edit/{id}")
    public String edytujOsobe(@PathVariable("id") String id, Model model){
        model.addAttribute("osoba",om.findById(id));
        return "edytujOsobe";
    }

    @PostMapping("/listaOsob/edytuj")
    public String zamienOsobe(@Valid @ModelAttribute("osoba") Osoba osoba, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()) {
            return "edytujOsobe";
        }
        om.replacePerson(osoba);
        model.addAttribute("osoby",om.getAllPersons());
        return "listaOsob";
    }

    @GetMapping("listaOsob/import")
    public String csvImport(Model model) throws IOException {
        om.importCsvFile();
        model.addAttribute("osoby",om.getAllPersons());
        return "listaOsob";
    }

    @GetMapping("listaOsob/export")
    public String csvExport(Model model) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        om.exportCsvFile();
        model.addAttribute("osoby",om.getAllPersons());
        return "listaOsob";
    }

    @GetMapping("listaOsob/json/{id}")
    public String JSON(@PathVariable("id") String id,Model model) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(om.findById(id));
        model.addAttribute("JSON",result);
        return "JSON";
    }
}
