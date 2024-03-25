package DTOs.response;

import entidades.Factura;

import java.util.List;

public record ClienteFacturaDTO (long dniCliente,
                                 String nombre,
                                 String email,
                                 List<Factura> facturas){ }
