package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.turno.TurnoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.TurnoModificacionDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.dto.salida.paciente.PacienteSalidaDto;
import com.backend.clinicaodontologica.dto.salida.turno.TurnoSalidaDto;
import com.backend.clinicaodontologica.entity.Paciente;
import com.backend.clinicaodontologica.entity.Turno;
import com.backend.clinicaodontologica.exceptions.BadRequestException;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.TurnoRepository;
import com.backend.clinicaodontologica.service.ITurnoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private final TurnoRepository turnoRepository;
    private final ModelMapper modelMapper;
    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        configureMapping();
    }

//    @Override
//    public TurnoSalidaDto crearTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
//
//        Long pacienteId = turnoEntradaDto.getPacienteId();
//        Long odontologoId = turnoEntradaDto.getOdontologoId();
//
//        PacienteSalidaDto pacienteTurno = pacienteService.buscarPacientePorId(pacienteId);
//        LOGGER.info("PacienteSalidaDto: {}", pacienteTurno);
//        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(odontologoId);
//
//        if(pacienteTurno == null){
//            throw new BadRequestException("Este paciente no existe");
//        }
//        if(odontologoTurno == null){
//            throw new BadRequestException("Este odontologo no existe");
//        }
//
//
//
//
//
//        return null;
//    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {

        Turno turnoARegistrar = modelMapper.map(turnoEntradaDto, Turno.class);

        LOGGER.info("Turno a registrar: {} ", JsonPrinter.toString(turnoARegistrar));

        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoARegistrar.getPaciente().getId());

        LOGGER.info("PacienteTurno: {}", JsonPrinter.toString(paciente));

        if(paciente == null){
            throw new BadRequestException("Este paciente no existe");
        }

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turnoARegistrar.getOdontologo().getId());

        LOGGER.info("OdontologoTurno: {}", JsonPrinter.toString(odontologoSalidaDto));

        if(odontologoSalidaDto == null){
            throw new BadRequestException("Este odontologo no existe");
        }


        if(turnoEntradaDto.getFechaYHora() == null){
            throw new BadRequestException("No se ha especificado la fecha/hora del turno");
        }

        Turno turnoApersistir = turnoRepository.save(turnoARegistrar);
        LOGGER.info("TurnoAPersistir: {}", JsonPrinter.toString(turnoApersistir));

        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoApersistir, TurnoSalidaDto.class);

        LOGGER.info("TurnoSalida: {}", JsonPrinter.toString(turnoSalidaDto));

        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnoSalidaDtos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        if (LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnoSalidaDtos));
        return turnoSalidaDtos;
    }



    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {

        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
        } else LOGGER.error("El id no se encuentra registrado en la base de datos");

        return turnoEncontrado;
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoModificacionDto turnoModificacionDto) throws ResourceNotFoundException {
        Turno turnoRecibido = modelMapper.map(turnoModificacionDto, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(turnoRecibido.getId()).orElse(null);

        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar != null) {
            turnoAActualizar = turnoRecibido;
            turnoRepository.save(turnoAActualizar);

            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

        } else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            //lanzar excepcion correspondiente
            throw new ResourceNotFoundException("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
        }


        return turnoSalidaDto;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {

        if (turnoRepository.findById(id).orElse(null) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el turno con id {}", id);
            //excepcion a lanzar aqui
            throw new ResourceNotFoundException("No se ha encontrado el turno con id "+ id);
        }

    }



    private void configureMapping(){
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(modelMapper -> modelMapper.map
                        (TurnoEntradaDto::getPaciente, Turno::setPaciente))
                .addMappings(modelMapper -> modelMapper.map
                        (TurnoEntradaDto::getOdontologo, Turno::setOdontologo));

        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(modelMapper -> modelMapper.map
                        (Turno::getPaciente, TurnoSalidaDto::setPacienteSalidaDto))
                .addMappings(modelMapper -> modelMapper.map
                        (Turno::getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto));;


    }

}
