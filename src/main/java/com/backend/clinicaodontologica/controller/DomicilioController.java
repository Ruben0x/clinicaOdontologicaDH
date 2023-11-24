package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.paciente.DomicilioEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.DomicilioModificacionDto;
import com.backend.clinicaodontologica.dto.salida.paciente.DomicilioSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IDomicilioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/domicilios")
public class DomicilioController {
    private IDomicilioService domicilioService;


    public DomicilioController(IDomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    //POST
    @PostMapping("/registrar")
    public ResponseEntity<DomicilioSalidaDto> registrarDomicilio(@RequestBody @Valid DomicilioEntradaDto domicilio){
        return new ResponseEntity<>(domicilioService.registrarDomicilio(domicilio), HttpStatus.CREATED);
    }

    //PUT
    @PutMapping("/actualizar")
    public ResponseEntity<DomicilioSalidaDto> actualizarDomicilio(@RequestBody @Valid DomicilioModificacionDto domicilio){
        return new ResponseEntity<>(domicilioService.actualizarDomicilio(domicilio), HttpStatus.CREATED);
    }

    //GET
    @GetMapping("/listar")
    public ResponseEntity<List<DomicilioSalidaDto>> listarDomicilios(){

        return new ResponseEntity<>(domicilioService.listarDomicilios(), HttpStatus.OK);
    }

    //Get
    @GetMapping("/buscarId")
    public ResponseEntity<DomicilioSalidaDto> buscarDomicilioPorId(@RequestParam Long id){
        return new ResponseEntity<>(domicilioService.buscarDomicilioPorId(id), HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarDomicilio(@PathVariable Long id) throws ResourceNotFoundException {
        domicilioService.eliminarDomicilio(id);
        return new ResponseEntity<>("Domicilio eliminado correctamente", HttpStatus.OK);
    }
}
