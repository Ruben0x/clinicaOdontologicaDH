package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.paciente.PacienteEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.PacienteModificacionDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);
    List<PacienteSalidaDto> listarPacientes();
    PacienteSalidaDto buscarPacientePorId(Long id) throws ResourceNotFoundException;
    PacienteSalidaDto actualizarPaciente(PacienteModificacionDto pacienteModificacionDto) throws ResourceNotFoundException;
    void eliminarPaciente(Long id) throws ResourceNotFoundException;
    PacienteSalidaDto buscarPacientePorDni(Integer dni);
}
