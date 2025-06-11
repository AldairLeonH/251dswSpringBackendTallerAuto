/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import org.springframework.stereotype.Service;


import dsw.tallerbackend.dto.AgregarMaterialRequest;
import dsw.tallerbackend.dto.CotizacionMaterialResponse;
import dsw.tallerbackend.model.*;
import dsw.tallerbackend.reporistory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author Ciro
 */
@Service
public class CotizacionMaterialService {
    @Autowired
    private CotizacionRepository cotizacionRepo;

    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private CotizacionMaterialRepository cotizacionMaterialRepo;

    public CotizacionMaterialResponse agregarMaterialACotizacion(AgregarMaterialRequest request) {
        Cotizacion cotizacion = cotizacionRepo.findById(request.getIdCotizacion())
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        Material material = materialRepo.findById(request.getIdMaterial())
                .orElseThrow(() -> new RuntimeException("Material no encontrado"));

        CotizacionMaterialId id = new CotizacionMaterialId(request.getIdCotizacion(), request.getIdMaterial());

        if (cotizacionMaterialRepo.existsById(id)) {
            throw new RuntimeException("El material ya está agregado a esta cotización.");
        }

        CotizacionMaterial cotizacionMaterial = CotizacionMaterial.builder()
                .id(id)
                .cotizacion(cotizacion)
                .material(material)
                .cantidad(request.getCantidad())
                .build();

        cotizacionMaterialRepo.save(cotizacionMaterial);

        return CotizacionMaterialResponse.fromEntity(material);
    }    
}
