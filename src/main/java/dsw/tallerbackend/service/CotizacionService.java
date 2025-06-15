/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.CotizacionRequest;
import dsw.tallerbackend.dto.CotizacionResponse;
import dsw.tallerbackend.model.Cotizacion;
import dsw.tallerbackend.model.Ost;
import dsw.tallerbackend.reporistory.CotizacionRepository;
import dsw.tallerbackend.reporistory.OstRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ciro
 */
@Service
public class CotizacionService {
    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private OstRepository ostRepository;
    // Listar todas las cotizaciones
    public List<CotizacionResponse> listCotizaciones() {
        return CotizacionResponse.fromEntities(cotizacionRepository.findAll());
    }
    // Insertar una nueva cotización
    public CotizacionResponse insertCotizacion(CotizacionRequest request) {
        // Obtener el objeto Ost por ID
        Integer idOst = request.getIdOst();
        Ost ost = ostRepository.findById(idOst).get();
        if (ost == null) return new CotizacionResponse();

        // Construir la entidad Cotizacion
        Cotizacion cotizacion = Cotizacion.builder()
                .id(request.getId())
                .fecha(request.getFecha())
                .total(request.getTotal())
                .ost(ost)
                .build();

        // Guardar en la base de datos
        cotizacion = cotizacionRepository.save(cotizacion);

        // Convertir a DTO de respuesta
        return CotizacionResponse.fromEntity(cotizacion);
    } 

    public CotizacionResponse actualizarTotalCotizacion(Long idCotizacion, Double nuevoTotal) {
        Optional<Cotizacion> optional = cotizacionRepository.findById(idCotizacion);

        if (optional.isEmpty()) {
            throw new RuntimeException("No se encontró la cotización con ID: " + idCotizacion);
        }

        Cotizacion cotizacion = optional.get();
        cotizacion.setTotal(nuevoTotal);

        cotizacion = cotizacionRepository.save(cotizacion);

        return CotizacionResponse.fromEntity(cotizacion);
    }
    
}
