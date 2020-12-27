package com.TBDGis.TBDProyecto.repositories;
import com.TBDGis.TBDProyecto.models.Emergency;
import java.util.List;

public interface EmergencyRepository {
    public int countEmergency();
    public List<Emergency> getAllEmergencies();
    public Emergency getEmergencyById(Integer id);
    public Emergency createEmergency(Emergency emergencia);
    public void updateEmergency(int id, Emergency emergencia);
    public void deleteEmergency(int id, Emergency emergencia);
}
