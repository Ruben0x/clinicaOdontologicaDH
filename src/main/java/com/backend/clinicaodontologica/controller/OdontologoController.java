package com.backend.clinicaodontologica.controller;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.service.IOdontologoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@CrossOrigin
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //POST
    @Operation(summary = "Registro de un nuevo odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo registrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.registrarOdontologo(odontologo), HttpStatus.CREATED);
    }

    //PUT
    @Operation(summary = "Actualizar datos de un odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Odontólogo actualizado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody @Valid OdontologoModificacionDto odontologo) throws ResourceNotFoundException {
        return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologo), HttpStatus.CREATED);
    }

    //GET
    @Operation(summary = "Listar todos los odontólogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogos listados correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    //Get
    @Operation(summary = "Buscar un odontólogo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Odontólogo encontrado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = OdontologoSalidaDto.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @GetMapping("/buscar/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id) throws ResourceNotFoundException {

        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un odontólogo por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Odontólogo eliminado correctamente",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Id inválido",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Odontólogo no encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content)
    })
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontólogo eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
