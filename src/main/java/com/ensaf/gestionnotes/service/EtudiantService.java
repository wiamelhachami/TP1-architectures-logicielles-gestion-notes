package com.ensaf.gestionnotes.service;

import com.ensaf.gestionnotes.model.Etudiant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EtudiantService {

    // Notre "base de données" : un dictionnaire (clé = nom + prénom)
    private final Map<String, Etudiant> etudiants = new LinkedHashMap<>();

    private static final double NOTE_VALIDATION = 12.0;

    // Construit la clé du dictionnaire (insensible à la casse)
    private String cle(String nom, String prenom) {
        return (nom.trim() + " " + prenom.trim()).toLowerCase();
    }

    // 1. Ajouter un étudiant
    public Etudiant ajouter(Etudiant e) {
        etudiants.put(cle(e.getNom(), e.getPrenom()), e);
        return e;
    }

    // 2. Affichage de tous les étudiants et leurs notes
    public List<Etudiant> afficherNotes() {
        return new ArrayList<>(etudiants.values());
    }

    // 3. Rechercher un étudiant (null s'il n'existe pas)
    public Etudiant rechercher(String nom, String prenom) {
        return etudiants.get(cle(nom, prenom));
    }

    // 4. Étudiants admis (moyenne >= 12)
    public List<Etudiant> admis() {
        return etudiants.values().stream()
                .filter(e -> e.getMoyenne() >= NOTE_VALIDATION)
                .collect(Collectors.toList());
    }

    // 5. Liste de rattrapage (moyenne < 12)
    public List<Etudiant> rattrapage() {
        return etudiants.values().stream()
                .filter(e -> e.getMoyenne() < NOTE_VALIDATION)
                .collect(Collectors.toList());
    }

    // 6. Majorant : le ou les étudiants avec la meilleure moyenne
    public List<Etudiant> majorant() {
        double max = etudiants.values().stream()
                .mapToDouble(Etudiant::getMoyenne)
                .max()
                .orElse(0);
        return etudiants.values().stream()
                .filter(e -> e.getMoyenne() == max)
                .collect(Collectors.toList());
    }

    // 7. Liste triée par moyenne décroissante
    public List<Etudiant> listeTriee() {
        return etudiants.values().stream()
                .sorted(Comparator.comparingDouble(Etudiant::getMoyenne).reversed())
                .collect(Collectors.toList());
    }
}
