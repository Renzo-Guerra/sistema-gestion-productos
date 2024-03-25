package DTOs.response;

import entidades.Cliente;
import entidades.Producto;

public record FacturaDetailedDTO (long idFactura,
                                  Producto producto,
                                  Cliente cliente,
                                  int cantidad,
                                  double totalPagar){ }
