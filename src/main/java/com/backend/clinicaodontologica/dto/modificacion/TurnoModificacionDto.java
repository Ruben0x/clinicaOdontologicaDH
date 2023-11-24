package com.backend.clinicaodontologica.dto.modificacion;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoModificacionDto {

    @NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El odontologo no puede ser nulo")
    private Long odontologoId;

    @NotNull(message = "El paciente no puede ser nulo")
    private Long pacienteId;


    public TurnoModificacionDto() {
    }

    public TurnoModificacionDto(Long id, LocalDateTime fechaYHora, Long odontologoId, Long pacienteId) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoId = odontologoId;
        this.pacienteId = pacienteId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
}
