package DTOs.response;

public record FacturaDTO (long idFactura,
                          long idProducto,
                          long idCliente,
                          int cantidad,
                          double totalPagar){ }
