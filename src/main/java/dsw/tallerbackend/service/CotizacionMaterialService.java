/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import org.springframework.stereotype.Service;


import dsw.tallerbackend.dto.AgregarMaterialRequest;
import dsw.tallerbackend.dto.AgregarMultiplesMaterialesRequest;
import dsw.tallerbackend.dto.CotizacionMaterialResponse;
import dsw.tallerbackend.dto.CotizacionMultiplesMaterialesResponse;
import dsw.tallerbackend.dto.MaterialCotizacionRequest;
import dsw.tallerbackend.model.*;
import dsw.tallerbackend.reporistory.*;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import static org.hibernate.internal.CoreLogging.logger;
import static org.hibernate.internal.HEMLogging.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger logger = LoggerFactory.getLogger(CotizacionMaterialService.class);

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
    @Transactional
    public CotizacionMultiplesMaterialesResponse agregarMultiplesMaterialesACotizacion(
            AgregarMultiplesMaterialesRequest request) {

        Cotizacion cotizacion = cotizacionRepo.findById(request.getIdCotizacion())
                .orElseThrow(() -> new RuntimeException("Cotización no encontrada"));

        List<CotizacionMaterialResponse> materialesAgregados = new ArrayList<>();
        List<String> errores = new ArrayList<>();

        for (MaterialCotizacionRequest materialReq : request.getMateriales()) {
            try {
                Material material = materialRepo.findById(materialReq.getIdMaterial())
                        .orElseThrow(() -> new RuntimeException("Material no encontrado"));

                CotizacionMaterialId id = new CotizacionMaterialId(
                        request.getIdCotizacion(), 
                        materialReq.getIdMaterial());

                if (!cotizacionMaterialRepo.existsById(id)) {
                    CotizacionMaterial cotizacionMaterial = CotizacionMaterial.builder()
                            .id(id)
                            .cotizacion(cotizacion)
                            .material(material)
                            .cantidad(materialReq.getCantidad())
                            .build();

                    cotizacionMaterialRepo.save(cotizacionMaterial);
                    materialesAgregados.add(CotizacionMaterialResponse.fromEntity(material));
                } else {
                    errores.add("Material " + materialReq.getIdMaterial() + " ya existe en cotización");
                }
            } catch (Exception e) {
                errores.add("Error con material " + materialReq.getIdMaterial() + ": " + e.getMessage());
            }
        }

        if (!errores.isEmpty()) {
            logger.warn("Errores al agregar materiales: {}", String.join(", ", errores));
        }

        return CotizacionMultiplesMaterialesResponse.builder()
                .idCotizacion(request.getIdCotizacion())
                .materialesAgregados(materialesAgregados)
                .build();
    }    
}
