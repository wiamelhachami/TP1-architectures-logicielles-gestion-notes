package com.ensaf.gestionnotes.controller;

import com.ensaf.gestionnotes.model.Etudiant;
import com.ensaf.gestionnotes.service.EtudiantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private final EtudiantService service;

    // Injection du service par Spring (via le constructeur)
    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    // 1. Ajouter un étudiant : POST /etudiants/ajouter
    @PostMapping("/ajouter")
    public Etudiant ajouter(@RequestBody Etudiant etudiant) {
        return service.ajouter(etudiant);
    }

    // 2. Affichage des notes : GET /etudiants/notes
    @GetMapping("/notes")
    public List<Etudiant> afficherNotes() {
        return service.afficherNotes();
    }

    // 3. Rechercher un étudiant : GET /etudiants/rechercher?nom=...&prenom=...
    @GetMapping("/rechercher")
    public ResponseEntity<Etudiant> rechercher(@RequestParam String nom,
                                               @RequestParam String prenom) {
        Etudiant e = service.rechercher(nom, prenom);
        if (e == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(e);
    }

    // 4. Étudiants admis : GET /etudiants/admis
    @GetMapping("/admis")
    public List<Etudiant> admis() {
        return service.admis();
    }

    // 5. Liste de rattrapage : GET /etudiants/rattrapage
    @GetMapping("/rattrapage")
    public List<Etudiant> rattrapage() {
        return service.rattrapage();
    }

    // 6. Majorant : GET /etudiants/majorant
    @GetMapping("/majorant")
    public List<Etudiant> majorant() {
        return service.majorant();
    }

    // 7. Liste triée : GET /etudiants/triee
    @GetMapping("/triee")
    public List<Etudiant> listeTriee() {
        return service.listeTriee();
    }
}