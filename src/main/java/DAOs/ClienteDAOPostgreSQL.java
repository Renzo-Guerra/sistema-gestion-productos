package DAOs;

import DTOs.request.AgregarClienteDTO;
import DTOs.response.ClienteDTO;
import DTOs.response.ClienteFacturaDTO;
import DTOs.response.ClienteTotalGastadoDto;
import entidades.Cliente;
import interfaces.IClienteDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import singleton.Conection;

import java.util.List;

public class ClienteDAOPostgreSQL implements IClienteDAO {
    @Override
    public void addCliente(AgregarClienteDTO cliente) {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        em.persist(new Cliente(cliente.dniCliente(), cliente.nombre(), cliente.email()));
        em.getTransaction().commit();
    }

    @Override
    public List<ClienteDTO> getClientes() {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        List<ClienteDTO> clientes = em.createQuery("SELECT new DTOs.response.ClienteDTO(c.dniCliente, c.nombre, c.email) FROM Cliente c ", DTOs.response.ClienteDTO.class).getResultList();
        em.getTransaction().commit();
        return clientes;
    }

    @Override
    public Cliente getCliente(long dni) {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        Cliente cliente = em.createQuery("SELECT c FROM Cliente c WHERE c.dniCliente = ?1", Cliente.class).setParameter(1, dni).getSingleResult();
        em.getTransaction().commit();
        return cliente;
    }

    @Override
    public ClienteFacturaDTO getClienteConFactura(long dni) {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        ClienteFacturaDTO cliente = em.createQuery("SELECT new DTOs.response.ClienteFacturaDTO(c.dniCliente, c.nombre, c.email, c.facturas) FROM Cliente c JOIN FETCH c.facturas", DTOs.response.ClienteFacturaDTO.class).getSingleResult();
        em.getTransaction().commit();
        return cliente;
    }

    @Override
    public void deleteCliente(long dni) {
        // TODO
    }

    @Override
    public List<ClienteTotalGastadoDto> getClientesOrdenadosPorMayorFacturacion() {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        Query query = em.createQuery(""" 
                                                SELECT new DTOs.response.ClienteTotalGastadoDto(c.id, c.nombre, c.email, sum(f.totalPagar))
                                                FROM Cliente c
                                                JOIN FETCH Factura f ON c.id = f.cliente.id
                                                GROUP BY c.id
                                                ORDER BY sum(f.totalPagar) DESC
                                            """, DTOs.response.ClienteTotalGastadoDto.class);
        List<ClienteTotalGastadoDto> clientes =  query.getResultList();
        em.getTransaction().commit();
        return clientes;
    }
}
