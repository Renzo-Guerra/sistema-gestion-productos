package DTOs.response;

public record ProductoMasRecaudacionDTO (long idProducto,
                                         String nombre,
                                         double precio,
                                         long unidadesVendidas,
                                         double totalRecaudado){ }
