package DTOs.response;

public record ClienteTotalGastadoDto(long idCliente,
                                     String nombre,
                                     String email,
                                     double totalGastado) { }
