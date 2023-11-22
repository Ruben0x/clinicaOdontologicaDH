package com.backend.clinicaodontologica.dto.entrada.turno;


import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {

    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El odontologo no puede ser nulo")
//    @NotBlank(message = "Debe especificarse el odontologo")
    private Odontologo odontologo;

    @NotNull(message = "El paciente no puede ser nulo")
    @Valid
//    @NotBlank(message = "Debe especificarse el paciente")
    private Paciente paciente;

    public TurnoEntradaDto(LocalDateTime fechaYHora, Odontologo odontologo, Paciente paciente) {
        this.fechaYHora = fechaYHora;
        this.odontologo = odontologo;
        this.paciente = paciente;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
}
