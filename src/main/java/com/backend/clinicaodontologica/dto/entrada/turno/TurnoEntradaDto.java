package com.backend.clinicaodontologica.dto.entrada.turno;


import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.entity.Paciente;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {

    @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
    @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
    private LocalDateTime fechaYHora;

    @NotNull(message = "El turno tiene que tener un odontólogo registrado")
    private Long odontologoId;

    @NotNull(message = "El turno tiene que tener un paciente registrado")
    private Long pacienteId;





//    @NotNull(message = "El odontologo no puede ser nulo")
////    @NotBlank(message = "Debe especificarse el odontologo")
//    private Odontologo odontologo;
//
//    @NotNull(message = "El paciente no puede ser nulo")
//    @Valid
////    @NotBlank(message = "Debe especificarse el paciente")
//    private Paciente paciente;


    public TurnoEntradaDto(Long pacienteId, Long odontologoId, LocalDateTime fechaYHora) {
        this.pacienteId = pacienteId;
        this.odontologoId = odontologoId;
        this.fechaYHora = fechaYHora;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
        this.fechaYHora = fechaYHora;
    }
}
