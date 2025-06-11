/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

/**
 *
 * @author Ciro
 */
import dsw.tallerbackend.dto.AgregarServicioRequest;
import dsw.tallerbackend.dto.CotizacionServicioResponse;
import dsw.tallerbackend.model.*;
import dsw.tallerbackend.reporistory.CotizacionRepository;
import dsw.tallerbackend.reporistory.CotizacionServicioRepository;
import dsw.tallerbackend.reporistory.ServicioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CotizacionServicioService {
    @Autowired
    private CotizacionRepository cotizacionRepo;

    @Autowired
    private ServicioRepository servicioRepo;

    @Autowired
    private CotizacionServicioRepository cotizacionServicioRepo;

    public CotizacionServicioResponse agregarServicioACotizacion(AgregarServicioRequest request) {
        Cotizacion cotizacion = cotizacionRepo.findById(request.getIdCotizacion())
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        Servicio servicio = servicioRepo.findById(request.getIdServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        CotizacionServicioId id = new CotizacionServicioId(request.getIdCotizacion(), request.getIdServicio());

        // Verificar si ya existe la relación
        if (cotizacionServicioRepo.existsById(id)) {
            throw new RuntimeException("El servicio ya está agregado a esta cotización.");
        }

        CotizacionServicio cotizacionServicio = CotizacionServicio.builder()
                .id(id)
                .cotizacion(cotizacion)
                .servicio(servicio)
                .build();

        cotizacionServicioRepo.save(cotizacionServicio); // Guardar en la BD

        return CotizacionServicioResponse.fromEntity(servicio);
    }    
}
