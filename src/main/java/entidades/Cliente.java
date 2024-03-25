package entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "dni_cliente")
    private long dniCliente;
    @Length(min = 3)
    private String nombre;
    @Email()
    private String email;
    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas;

    public Cliente(long dniCliente, String nombre, String email){
        this.dniCliente = dniCliente;
        this.nombre = nombre;
        this.email = email;
        this.facturas = new ArrayList<>();
    }
}
