package com.backend.clinicaodontologica.dto.modificacion;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoModificacionDto {

    @NotNull(message = "Debe proveerse el id del turno que se desea modificar")
    private Long id;

    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El odontologo no puede ser nulo")
    @Valid
    private OdontologoModificacionDto odontologoModificacionDto;

    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
    private PacienteModificacionDto pacienteModificacionDto;


    public TurnoModificacionDto() {
    }

    public TurnoModificacionDto(Long id, LocalDateTime fechaYHora, OdontologoModificacionDto odontologoModificacionDto, PacienteModificacionDto pacienteModificacionDto) {
        this.id = id;
        this.fechaYHora = fechaYHora;
        this.odontologoModificacionDto = odontologoModificacionDto;
        this.pacienteModificacionDto = pacienteModificacionDto;
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

    public OdontologoModificacionDto getOdontologoModificacionDto() {
        return odontologoModificacionDto;
    }

    public void setOdontologoModificacionDto(OdontologoModificacionDto odontologoModificacionDto) {
        this.odontologoModificacionDto = odontologoModificacionDto;
    }

    public PacienteModificacionDto getPacienteModificacionDto() {
        return pacienteModificacionDto;
    }

    public void setPacienteModificacionDto(PacienteModificacionDto pacienteModificacionDto) {
        this.pacienteModificacionDto = pacienteModificacionDto;
    }
}
