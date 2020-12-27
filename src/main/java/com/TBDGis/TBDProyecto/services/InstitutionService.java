package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.Institution;
import com.TBDGis.TBDProyecto.repositories.InstitutionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "institucion")
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/getAll")
    public List<Institution> getAllInstitutions() {
        return institutionRepository.getAllInstituciones();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Institution getInstitutionById(@PathVariable(value = "id") Integer id) {
        return this.institutionRepository.getInstitutionById(id);
    }

    @GetMapping("/count")
    public String countInstitutions() {
        int total = institutionRepository.countInstitutions();
        return String.format("Tienes %s institutiones!!", total);
    }

    @PostMapping("/createInstitution")
    @ResponseBody
    public Institution createInstitution(@RequestBody Institution institution) {
        Institution result = institutionRepository.createInstitution(institution);
        return result;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public void updateInstitution(@PathVariable(value = "id") int id, Institution institution) {
        institutionRepository.updateInstitution(id, institution);
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteInstitution(@PathVariable(value = "id") int id, Institution institution){
        institutionRepository.deleteInstitution(id, institution);
    }
}
