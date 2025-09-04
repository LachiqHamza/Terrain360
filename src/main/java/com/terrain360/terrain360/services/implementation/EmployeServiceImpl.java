package com.terrain360.terrain360.services.implementation;


import com.terrain360.terrain360.DTO.CreateEmployeDTO;
import com.terrain360.terrain360.DTO.EmployeDTO;
import com.terrain360.terrain360.DTO.UpdateEmployeDTO;
import com.terrain360.terrain360.entities.Employe;
import com.terrain360.terrain360.entities.Role;
import com.terrain360.terrain360.repositories.EmployeRepository;
import com.terrain360.terrain360.services.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeServiceImpl implements EmployeService {

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    @Transactional
    public EmployeDTO saveEmploye(CreateEmployeDTO employeDTO) {
        if (employeRepository.existsByNomDeUtilisateur(employeDTO.getNomDeUtilisateur())) {
            throw new IllegalArgumentException("Nom d'utilisateur déjà utilisé");
        }

        if (employeRepository.existsByEmail(employeDTO.getEmail())) {
            throw new IllegalArgumentException("Email déjà utilisé");
        }

        Employe employe = new Employe();
        employe.setNom(employeDTO.getNom());
        employe.setPrenom(employeDTO.getPrenom());
        employe.setNomDeUtilisateur(employeDTO.getNomDeUtilisateur());
        employe.setMotDePasse(passwordEncoder.encode(employeDTO.getMotDePasse()));
        employe.setTelephone(employeDTO.getTelephone());
        employe.setEmail(employeDTO.getEmail());
        employe.setRole(employeDTO.getRole());
        employe.setDepartement(employeDTO.getDepartement());
        employe.setPoste(employeDTO.getPoste());
        employe.setActif(true);

        Employe savedEmploye = employeRepository.save(employe);
        return convertToDTO(savedEmploye);
    }

    @Override
    public List<EmployeDTO> getAllEmployes() {
        return employeRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeDTO getEmployeById(Long id) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        return convertToDTO(employe);
    }

    @Override
    @Transactional
    public EmployeDTO updateEmploye(Long id, UpdateEmployeDTO updatedDTO) {
        Optional<Employe> optional = employeRepository.findById(id);
        if (optional.isPresent()) {
            Employe e = optional.get();
            e.setNom(updatedDTO.getNom());
            e.setPrenom(updatedDTO.getPrenom());
            e.setEmail(updatedDTO.getEmail());
            e.setTelephone(updatedDTO.getTelephone());
            e.setPoste(updatedDTO.getPoste());
            e.setAdresse(updatedDTO.getAdresse());
            e.setDepartement(updatedDTO.getDepartement());
            e.setSalaire(updatedDTO.getSalaire());

            Employe updatedEmploye = employeRepository.save(e);
            return convertToDTO(updatedEmploye);
        }
        throw new RuntimeException("Employé non trouvé avec l'ID: " + id);
    }

    @Override
    @Transactional
    public void deleteEmploye(Long id) {
        Employe employe = employeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        employe.setActif(false);
        employeRepository.save(employe);
    }

    @Override
    public List<EmployeDTO> getEmployesByRole(Role role) {
        return employeRepository.findByRole(role).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmployeDTO convertToDTO(Employe employe) {
        EmployeDTO dto = new EmployeDTO();
        dto.setId(employe.getId());
        dto.setNom(employe.getNom());
        dto.setPrenom(employe.getPrenom());
        dto.setDateNaissance(employe.getDateNaissance());
        dto.setAdresse(employe.getAdresse());
        dto.setNomDeUtilisateur(employe.getNomDeUtilisateur());
        dto.setTelephone(employe.getTelephone());
        dto.setImageProfil(employe.getImageProfil());
        dto.setGenre(employe.getGenre());
        dto.setRole(employe.getRole());
        dto.setEmail(employe.getEmail());
        dto.setDateEmbauche(employe.getDateEmbauche());
        dto.setMatricule(employe.getMatricule());
        dto.setDepartement(employe.getDepartement());
        dto.setPoste(employe.getPoste());
        dto.setSalaire(employe.getSalaire());
        dto.setActif(employe.getActif());
        return dto;
    }
}