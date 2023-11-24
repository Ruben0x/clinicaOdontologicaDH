package com.backend.clinicaodontologica.service;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.DomicilioModificacionDto;
import com.backend.clinicaodontologica.dto.salida.paciente.DomicilioSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IDomicilioService {

    DomicilioSalidaDto registrarDomicilio(DomicilioEntradaDto domicilioEntradaDto);
    List<DomicilioSalidaDto> listarDomicilios();
    DomicilioSalidaDto buscarDomicilioPorId(Long id);
    DomicilioSalidaDto actualizarDomicilio(DomicilioModificacionDto domicilioModificacionDto);
    void eliminarDomicilio(Long id) throws ResourceNotFoundException;


}
