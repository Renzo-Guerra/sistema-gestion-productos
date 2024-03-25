package interfaces;

import DTOs.request.AgregarClienteDTO;
import DTOs.response.ClienteDTO;
import DTOs.response.ClienteFacturaDTO;
import DTOs.response.ClienteTotalGastadoDto;
import entidades.Cliente;

import java.util.List;

public interface IClienteDAO {
    void addCliente(AgregarClienteDTO cliente);
    List<ClienteDTO> getClientes();
    Cliente getCliente(long dni);
    ClienteFacturaDTO getClienteConFactura(long dni);
    void deleteCliente(long dni);

    List<ClienteTotalGastadoDto> getClientesOrdenadosPorMayorFacturacion();
}