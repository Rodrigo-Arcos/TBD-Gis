package com.TBDGis.TBDProyecto.models;

import org.postgis.PGgeometry;
import org.json.*;

public class Volunteer {

    // Attributes
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String sexo;
    private JSONObject dimensions;
    private JSONObject requeriments;
    private double longitude;
    private double latitude;
    private PGgeometry location;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public JSONObject getDimensions() {
        return dimensions;
    }

    public void setDimensions(JSONObject dimensions) {
        this.dimensions = dimensions;
    }

    public JSONObject getRequeriments() {
        return requeriments;
    }

    public void setRequeriments(JSONObject requeriments) {
        this.requeriments = requeriments;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public PGgeometry getLocation() {
        return location;
    }

    public void setLocation(PGgeometry location) {
        this.location = location;
    }

    // Additional methods and content

}
