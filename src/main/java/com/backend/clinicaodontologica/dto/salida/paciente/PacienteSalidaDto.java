package com.backend.clinicaodontologica.dto.salida.paciente;

import java.time.LocalDate;

public class PacienteSalidaDto {
    private Long id;
    private String nombre;
    private String apellido;
    private Integer dni;
    private LocalDate fechaIngreso;
    private DomicilioSalidaDto domicilioSalidaDto;

    public PacienteSalidaDto(Long id, String nombre, String apellido, Integer dni, LocalDate fechaIngreso, DomicilioSalidaDto domicilio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.domicilioSalidaDto = domicilio;
    }

    public PacienteSalidaDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public DomicilioSalidaDto getDomicilioSalidaDto() {
        return domicilioSalidaDto;
    }

    public void setDomicilioSalidaDto(DomicilioSalidaDto domicilioSalidaDto) {
        this.domicilioSalidaDto = domicilioSalidaDto;
    }
}
