/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.controller;

import dsw.tallerbackend.dto.ItemInventarioDTO;
import dsw.tallerbackend.service.ItemInventarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item-inventario")
@CrossOrigin(origins = "*")
public class ItemInventarioController {
    
    @Autowired
    private ItemInventarioService itemService;

    @GetMapping
    public List<ItemInventarioDTO> listar() {
        return itemService.listarItems();
    }
}
