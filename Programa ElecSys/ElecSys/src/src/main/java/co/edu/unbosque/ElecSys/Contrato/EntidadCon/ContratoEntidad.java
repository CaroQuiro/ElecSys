package co.edu.unbosque.ElecSys.Contrato.EntidadCon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table (name = "contrato")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ContratoEntidad {

    @Id
    @Column(name = "id_contrato")
    private int id_contrato;

    @Column(name = "id_trabajador")
    private int id_trabajador;

    @Column(name = "sueldo")
    private BigDecimal sueldo;

    @Column(name = "fecha_expedicion")
    private Date fecha_expedicion;

    @Column(name = "fecha_iniciacion")
    private Date fecha_iniciacion;

    @Column(name = "id_trabajador_encargado")
    private int id_trabajador_encargado;
}
