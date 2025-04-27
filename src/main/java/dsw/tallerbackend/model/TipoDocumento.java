/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dsw.tallerbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


class TipoDocumento {
    @Id
    @Column(name="id_tipo_doc")
    private int idTipoDoc;
    @Column(name="tipo_doc")
    private String tipoDoc;    
}
