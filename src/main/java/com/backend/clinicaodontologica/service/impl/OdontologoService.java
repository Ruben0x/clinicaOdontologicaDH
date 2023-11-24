package com.backend.clinicaodontologica.service.impl;

import com.backend.clinicaodontologica.dto.entrada.odontologo.OdontologoEntradaDto;
import com.backend.clinicaodontologica.dto.modificacion.OdontologoModificacionDto;
import com.backend.clinicaodontologica.dto.salida.odontologo.OdontologoSalidaDto;
import com.backend.clinicaodontologica.entity.Odontologo;
import com.backend.clinicaodontologica.exceptions.ResourceNotFoundException;
import com.backend.clinicaodontologica.repository.OdontologoRepository;
import com.backend.clinicaodontologica.service.IOdontologoService;
import com.backend.clinicaodontologica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private OdontologoRepository odontologoRepository;
    private ModelMapper modelMapper;

    public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
        configureMapping();
    }

    @Override
    public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {

        //convertimos mediante el mapper de dtoEntrada a entidad
        LOGGER.info("OdontologoEntradaDto: " + JsonPrinter.toString(odontologoEntradaDto));
        Odontologo odontologoEntidad = modelMapper.map(odontologoEntradaDto, Odontologo.class);

        //mandamos a persistir a la capa dao y obtenemos una entidad
        Odontologo odontologoAPersistir = odontologoRepository.save(odontologoEntidad);

        //transformamos la entidad obtenida en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoAPersistir, OdontologoSalidaDto.class);
        LOGGER.info("OdontologoSalidaDto: " + JsonPrinter.toString(odontologoSalidaDto));

        return odontologoSalidaDto;
    }
    @Override
    public List<OdontologoSalidaDto> listarOdontologos() {
        List<OdontologoSalidaDto> odontologoSalidaDtos = odontologoRepository.findAll()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();

        if(LOGGER.isInfoEnabled())
            LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologoSalidaDtos));
        return odontologoSalidaDtos;
    }
    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) throws ResourceNotFoundException {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if(odontologoBuscado!=null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("OdontologoSalidaDto: {}", JsonPrinter.toString(odontologoEncontrado));
        }else {
            LOGGER.error("El id no se encuentra registrado en la base de datos");
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id "+ id);
        }


        return odontologoEncontrado;
    }

    @Override
    public OdontologoSalidaDto actualizarOdontologo(OdontologoModificacionDto odontologoModificacionDto) throws ResourceNotFoundException {

        Odontologo odontolgoRecibido = modelMapper.map(odontologoModificacionDto, Odontologo.class);
        Odontologo odontologoAActualizar = odontologoRepository.findById(odontolgoRecibido.getId()).orElse(null);

        OdontologoSalidaDto odontologoSalidaDto = null;

        if(odontologoAActualizar!=null){
            odontologoAActualizar = odontolgoRecibido;
            odontologoRepository.save(odontologoAActualizar);

            odontologoSalidaDto = modelMapper.map(odontologoAActualizar, OdontologoSalidaDto.class);
            LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoAActualizar));
        }else{
            LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");

        }


        return odontologoSalidaDto;
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException{

        if (odontologoRepository.findById(id).orElse(null) != null) {
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el odontologo con id: {}", id);
        } else {
            LOGGER.error("No se ha encontrado el odontologo con id {}", id);
            //excepcion a lanzar aqui
            throw new ResourceNotFoundException("No se ha encontrado el odontologo con id "+ id);
        }

    }


    private void configureMapping(){
        modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class);
        modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class);
    }



}
