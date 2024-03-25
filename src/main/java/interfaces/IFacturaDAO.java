package interfaces;

import DTOs.request.AgregarFacturaDTO;
import DTOs.response.FacturaDTO;
import DTOs.response.FacturaDetailedDTO;

import java.util.List;

public interface IFacturaDAO {
    void addFactura(IClienteDAO clienteDAO, IProductoDAO productoDAO, AgregarFacturaDTO factura);
    List<FacturaDetailedDTO> getFacturasDetailedInfo();
    List<FacturaDTO> getFacturas();
    FacturaDTO getFactura(long id);
    FacturaDetailedDTO getFacturaDetailedInfo(long id);
    void deleteFactura(long id);
}
