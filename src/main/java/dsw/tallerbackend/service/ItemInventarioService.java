/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.ItemInventarioDTO;
import dsw.tallerbackend.reporistory.ItemInventarioRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInventarioService {
        @Autowired
    private ItemInventarioRepository itemRepo;

    public List<ItemInventarioDTO> listarItems() {
        return itemRepo.findAll().stream()
                .map(ItemInventarioDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
