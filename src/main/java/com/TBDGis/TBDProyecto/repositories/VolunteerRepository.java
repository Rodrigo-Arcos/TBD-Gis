package com.TBDGis.TBDProyecto.repositories;

import com.TBDGis.TBDProyecto.models.Volunteer;

import java.util.List;

public interface VolunteerRepository {

    public List<Volunteer> getAllVolunteers();
    public Volunteer getVolunteerById(Integer id);
    public Volunteer createVolunteer(Volunteer voluntario);

}
