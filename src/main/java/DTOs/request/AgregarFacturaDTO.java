package DTOs.request;

public record AgregarFacturaDTO (long idProducto,
                                long idCliente,
                                int cantidad){ }
