/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.AgregarMaterialRequest;
import dsw.tallerbackend.dto.AgregarServicioRequest;
import dsw.tallerbackend.dto.CotizacionMaterialResponse;
import dsw.tallerbackend.dto.CotizacionServicioResponse;
import dsw.tallerbackend.service.CotizacionMaterialService;
import dsw.tallerbackend.service.CotizacionServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/cotizaciones")
public class CotizacionController {
    @Autowired
    private CotizacionServicioService cotizacionServicioService;
    @Autowired
    private CotizacionMaterialService cotizacionMaterialService;
    

    @PostMapping("/agregar-servicio")
    public CotizacionServicioResponse agregarServicio(@RequestBody AgregarServicioRequest request) {
        return cotizacionServicioService.agregarServicioACotizacion(request);
    }
    @PostMapping("/agregar-material")
    public CotizacionMaterialResponse agregarMaterial(@RequestBody AgregarMaterialRequest request) {
        return cotizacionMaterialService.agregarMaterialACotizacion(request);
    }    
}
