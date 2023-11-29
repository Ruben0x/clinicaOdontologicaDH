package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoDeNombreBruce(){
      OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("ABCD", "Bruce", "Wayne");

      OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
      assertEquals("Bruce", odontologoSalidaDto.getNombre());
    }



  @Test
  @Order(2)
  void deberiaActualizarDatosDeOdontologoConId1() throws ResourceNotFoundException {

//      OdontologoEntradaDto odontologoARegistrar = new OdontologoEntradaDto("ABCD", "Bruce", "Wayne");
//      odontologoService.registrarOdontologo(odontologoARegistrar);

      OdontologoModificacionDto odontologoModificacionDto = new OdontologoModificacionDto(1L, "QWERTY", "PETER", "PORKER");
      OdontologoSalidaDto odontologoSalidaDto = odontologoService.actualizarOdontologo(odontologoModificacionDto);

      assertEquals("PETER", odontologoSalidaDto.getNombre());
  }

    @Test
    @Order(3)
    void deberiaDevolverUnaListaNoVaciaDePacientes(){
        List<OdontologoSalidaDto> odontologoSalidaDtoList = odontologoService.listarOdontologos();

        assertFalse(odontologoSalidaDtoList.isEmpty());
    }
}