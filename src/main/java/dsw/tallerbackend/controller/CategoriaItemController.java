/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.model.CategoriaItem;
import dsw.tallerbackend.service.CategoriaItemService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 

@RestController
@RequestMapping("/api/v1/categoria-item")
public class CategoriaItemController {
    @Autowired private CategoriaItemService categoriaItemService;
    
    @GetMapping
    public List<CategoriaItem> getCategorias() {
        return categoriaItemService.findAll();
    }
}
