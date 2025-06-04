
package dsw.tallerbackend.reporistory;

import dsw.tallerbackend.model.InventarioAuto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioAutoRepository extends JpaRepository<InventarioAuto, Integer> {
    List<InventarioAuto> findByOst_IdOst(Integer idOst);
}
