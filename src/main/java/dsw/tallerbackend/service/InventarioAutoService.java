package dsw.tallerbackend.service;

import dsw.tallerbackend.dto.InventarioAutoRequestDTO;
import dsw.tallerbackend.dto.InventarioAutoResponseDTO;
import dsw.tallerbackend.model.InventarioAuto;
import dsw.tallerbackend.model.Ost;
import dsw.tallerbackend.reporistory.InventarioAutoRepository;
import dsw.tallerbackend.reporistory.ItemInventarioRepository;
import dsw.tallerbackend.reporistory.OstRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarioAutoService {

    @Autowired private InventarioAutoRepository inventarioAutoRepository;
    @Autowired private  OstRepository ostRepository;
    @Autowired private ItemInventarioRepository itemRepository;
    /*
    public void registrarInventario(int idOst, List<InventarioAutoRequestDTO> items) {
    Ost ost = ostRepository.findById(idOst)
    .orElseThrow(() -> new RuntimeException("OST no encontrada"));
    
    for (InventarioAutoRequestDTO dto : items) {
    InventarioAuto inventario = InventarioAuto.builder()
    .idOst(ostRepository.findById(dto.getIdOst()).orElseThrow())
    .idItem(itemRepository.findById(dto.getIdItem()).orElseThrow())
    .cantidad(dto.getCantidad())
    .estado(dto.getEstado())
    .build();
    
    inventarioAutoRepository.save(inventario);
    }
    }*/
    public void guardarInventario(List<InventarioAutoRequestDTO> inventarioDTOs) {
        for (InventarioAutoRequestDTO dto : inventarioDTOs) {
            InventarioAuto inventario = InventarioAuto.builder()
                .ost(ostRepository.findById(dto.getIdOst()).orElseThrow())
                .idItem(itemRepository.findById(dto.getIdItem()).orElseThrow())
                .cantidad(dto.getCantidad())
                .estado(dto.getEstado())
                .build();

            inventarioAutoRepository.save(inventario);
        }
    }

    public List<InventarioAutoResponseDTO> obtenerPorOst(int idOst) {
        return inventarioAutoRepository.findByOst_IdOst(idOst)
                .stream()
                .map(InventarioAutoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
