package entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private long idFactura;
    @ManyToOne
    private Producto producto;
    @ManyToOne()
    private Cliente cliente;

    @Min(1)
    private int cantidad;
    @Min(1)
    private double totalPagar;

    public Factura(Producto producto, Cliente cliente, int cantidad){
        this.producto = producto;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.totalPagar = (producto.getPrecio() * cantidad);
    }
}
