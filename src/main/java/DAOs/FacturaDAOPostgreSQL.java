package DAOs;

import DTOs.request.AgregarFacturaDTO;
import DTOs.response.FacturaDTO;
import DTOs.response.FacturaDetailedDTO;
import entidades.Cliente;
import entidades.Factura;
import entidades.Producto;
import interfaces.IClienteDAO;
import interfaces.IFacturaDAO;
import interfaces.IProductoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.validation.Valid;
import singleton.Conection;

import java.util.List;

public class FacturaDAOPostgreSQL implements IFacturaDAO {
    @Override
    public void addFactura(IClienteDAO clienteDAO, IProductoDAO productoDAO, AgregarFacturaDTO factura) {
        Cliente cliente = clienteDAO.getCliente(factura.idCliente());
        if (cliente == null) {
            System.out.println("Error: No existe un cliente con el id '" + factura.idCliente() + "'.");
            return ;
        }

        Producto producto = productoDAO.getProducto(factura.idProducto());
        if (producto == null) {
            System.out.println("Error: No existe un producto con el id '" + factura.idProducto() + "'.");
            return ;
        }

        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        em.persist(new Factura(producto, cliente, factura.cantidad()));
        em.getTransaction().commit();
    }

    @Override
    public List<FacturaDetailedDTO> getFacturasDetailedInfo() {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        List<FacturaDetailedDTO> facturas = em.createQuery("SELECT new DTOs.response.FacturaDetailedDTO(f.idFactura, f.producto, f.cliente, f.cantidad, f.totalPagar) FROM Factura f", FacturaDetailedDTO.class).getResultList();
        em.getTransaction().commit();
        return facturas;
    }

    @Override
    public List<FacturaDTO> getFacturas() {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        List<FacturaDTO> facturas = em.createQuery("SELECT new DTOs.response.FacturaDTO(f.idFactura, f.producto.id, f.cliente.id, f.cantidad, f.totalPagar) FROM Factura f", FacturaDTO.class).getResultList();
        em.getTransaction().commit();
        return facturas;
    }

    @Override
    public FacturaDTO getFactura(long id) {
        if(id < 0){
            System.out.println("Exception: El id debe ser mayor que 0!");
            return null;
        }

        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();

        FacturaDTO factura = null;
        try{
            factura = em.createQuery("SELECT new DTOs.response.FacturaDTO(f.idFactura, f.producto.id, f.cliente.id, f.cantidad, f.totalPagar) FROM Factura f WHERE f.id = ?1", FacturaDTO.class).setParameter(1, id).getSingleResult();
        }catch (NoResultException e){
            System.out.println("Error: No existe una factura con el id '" + id + "'.");
        }
        em.getTransaction().commit();
        return factura;
    }

    @Override
    public FacturaDetailedDTO getFacturaDetailedInfo(long id) {
        if(id < 0){
            System.out.println("Exception: El id debe ser mayor que 0!");
            return null;
        }

        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        FacturaDetailedDTO factura = null;
        try{
            factura = em.createQuery("SELECT new DTOs.response.FacturaDetailedDTO(f.idFactura, f.producto, f.cliente, f.cantidad, f.totalPagar) FROM Factura f WHERE f.id = ?1", FacturaDetailedDTO.class).setParameter(1, id).getSingleResult();
        }catch (NoResultException e) {
            System.out.println("Error: No existe una factura con el id '" + id + "'.");
        }

        em.getTransaction().commit();
        return factura;
    }

    @Override
    public void deleteFactura(long id) {

    }
}
