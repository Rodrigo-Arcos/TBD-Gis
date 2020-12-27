package com.TBDGis.TBDProyecto.services;

import com.TBDGis.TBDProyecto.models.*;
import com.TBDGis.TBDProyecto.repositories.RolRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "rol")
public class RolService {

    private final RolRepository rolRepository;

    RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Rol> getAllRol() {
        return this.rolRepository.getAllRol();
    }

    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Rol getRolById(@PathVariable(value = "id") Integer id) {
        return this.rolRepository.getRolById(id);
    }

    @PostMapping("/createRol")
    @ResponseBody
    public Rol createRol(@RequestBody Rol v) {
        Rol newRol = rolRepository.createRol(v);
        return newRol;
    }

    @PutMapping("/delete/{id}")
    @ResponseBody
    public void deleteRol(@PathVariable(value = "id") int id, Rol rol) {
        rolRepository.deleteRol(id, rol);

    }
}
