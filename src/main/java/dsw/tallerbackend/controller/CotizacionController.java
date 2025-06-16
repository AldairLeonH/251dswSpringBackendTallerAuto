/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.ActualizarTotalCotizacionRequest;
import dsw.tallerbackend.dto.AgregarMaterialRequest;
import dsw.tallerbackend.dto.AgregarMultiplesMaterialesRequest;
import dsw.tallerbackend.dto.AgregarMultiplesServiciosRequest;
import dsw.tallerbackend.dto.AgregarServicioRequest;
import dsw.tallerbackend.dto.CotizacionMaterialResponse;
import dsw.tallerbackend.dto.CotizacionMultiplesMaterialesResponse;
import dsw.tallerbackend.dto.CotizacionMultiplesServiciosResponse;
import dsw.tallerbackend.dto.CotizacionRequest;
import dsw.tallerbackend.dto.CotizacionResponse;
import dsw.tallerbackend.dto.CotizacionServicioResponse;
import dsw.tallerbackend.service.CotizacionMaterialService;
import dsw.tallerbackend.service.CotizacionService;
import dsw.tallerbackend.service.CotizacionServicioService;
import dsw.tallerbackend.utils.ErrorResponse;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cotizaciones")
public class CotizacionController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CotizacionServicioService cotizacionServicioService;

    @Autowired
    private CotizacionMaterialService cotizacionMaterialService;

    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping
    public ResponseEntity<?> getCotizaciones() {
        List<CotizacionResponse> listaCotizaciones = null;
        try {
            listaCotizaciones = cotizacionService.listCotizaciones();
        } catch (Exception e) {
            logger.error("Error inesperado al listar cotizaciones", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaCotizaciones.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("cotizaciones not found").build());
        }
        return ResponseEntity.ok(listaCotizaciones);
    }

    @PostMapping
    public ResponseEntity<?> insertCotizacion(@RequestBody CotizacionRequest request) {
        logger.info("> insertCotizacion: " + request.toString());
        CotizacionResponse response;
        try {
            response = cotizacionService.insertCotizacion(request);
        } catch (Exception e) {
            logger.error("Error inesperado al insertar cotizacion", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (response == null || response.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ErrorResponse.builder().message("cotizacion not inserted").build());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/agregar-servicio")
    public CotizacionServicioResponse agregarServicio(@RequestBody AgregarServicioRequest request) {
        return cotizacionServicioService.agregarServicioACotizacion(request);
    }

    @PostMapping("/agregar-material")
    public CotizacionMaterialResponse agregarMaterial(@RequestBody AgregarMaterialRequest request) {
        return cotizacionMaterialService.agregarMaterialACotizacion(request);
    }

    @PostMapping("/agregar-servicios")
    public ResponseEntity<?> agregarMultiplesServicios(@RequestBody AgregarMultiplesServiciosRequest request) {
        logger.info("> agregarMultiplesServicios: " + request.toString());

        if (request.getIdServicios() == null || request.getIdServicios().isEmpty()) {
            return ResponseEntity.ok(CotizacionMultiplesServiciosResponse.builder()
                    .idCotizacion(request.getIdCotizacion())
                    .serviciosAgregados(List.of())
                    .build());
        }

        try {
            CotizacionMultiplesServiciosResponse response = cotizacionServicioService.agregarMultiplesServiciosACotizacion(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Error al agregar múltiples servicios", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder()
                            .message("Error al agregar servicios: " + e.getMessage())
                            .build());
        }
    }

    @PostMapping("/agregar-materiales")
    public ResponseEntity<?> agregarMultiplesMateriales(
            @RequestBody AgregarMultiplesMaterialesRequest request) {

        logger.info("Solicitud para agregar materiales a cotización: {}", request.getIdCotizacion());

        if (request.getMateriales() == null || request.getMateriales().isEmpty()) {
            return ResponseEntity.ok(CotizacionMultiplesMaterialesResponse.builder()
                    .idCotizacion(request.getIdCotizacion())
                    .materialesAgregados(List.of())
                    .build());
        }

        try {
            CotizacionMultiplesMaterialesResponse response =
                    cotizacionMaterialService.agregarMultiplesMaterialesACotizacion(request);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Error al agregar materiales: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder()
                            .message("Error al procesar materiales: " + e.getMessage())
                            .build());
        }
    }

    @PostMapping("/actualizar-total")
    public ResponseEntity<?> actualizarTotalCotizacion(@RequestBody ActualizarTotalCotizacionRequest request) {
        logger.info("Actualizando total para cotización ID: {}", request.getIdCotizacion());

        try {
            cotizacionService.actualizarTotalCotizacion(
                    request.getIdCotizacion(), request.getNuevoTotal()
            );

            return ResponseEntity.ok(Map.of("mensaje", "Total actualizado correctamente"));
        } catch (Exception e) {
            logger.error("Error al actualizar el total de la cotización", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("mensaje", "Error al actualizar el total: " + e.getMessage()));
        }
    }
}
