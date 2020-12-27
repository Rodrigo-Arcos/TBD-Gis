package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Institution;

import java.util.List;

public interface InstitutionRepository {
    public int countInstitutions();
    public List<Institution> getAllInstituciones();
    public Institution getInstitutionById(Integer id);
    public Institution createInstitution(Institution institucion);
    public void updateInstitution(int id, Institution institucion);
    public void deleteInstitution(int id, Institution institucion);
}
