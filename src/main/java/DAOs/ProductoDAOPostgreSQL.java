package DAOs;

import DTOs.request.AgregarProductoDTO;
import DTOs.response.ProductoDTO;
import DTOs.response.ProductoMasRecaudacionDTO;
import entidades.Producto;
import interfaces.IProductoDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import singleton.Conection;

import java.util.List;

public class ProductoDAOPostgreSQL implements IProductoDAO {
    public void addProducto(AgregarProductoDTO producto){
        if(producto.precio() < 0){
            System.out.println("EXCEPCION: El precio del producto debe ser mayor a cero!");
            return ;
        }
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        em.persist(new Producto(producto.nombre(), producto.precio()));
        em.getTransaction().commit();
    }
    public List<ProductoDTO> getProductos(){
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        List<ProductoDTO> productos = em.createQuery("SELECT new DTOs.response.ProductoDTO(p.id, p.nombre, p.precio) FROM Producto p", DTOs.response.ProductoDTO.class).getResultList();
        em.getTransaction().commit();
        return productos;
    }
    public Producto getProducto(long id){
        if(id < 0){
            System.out.println("Exception: El id debe ser mayor que 0!");
            return null;
        }

        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        Producto producto = em.createQuery("SELECT p FROM Producto p WHERE p.id = ?1", Producto.class).setParameter(1, id).getSingleResult();
        em.getTransaction().commit();
        return producto;
    }

    @Override
    public List<ProductoMasRecaudacionDTO> getProductoConMayorRecaudacion() {
        EntityManager em = Conection.getEMInstance();
        em.getTransaction().begin();
        Query query = em.createQuery(""" 
                                                SELECT new DTOs.response.ProductoMasRecaudacionDTO(p.id, p.nombre, p.precio, max(f.cantidad), max(f.totalPagar))
                                                FROM Producto p
                                                JOIN FETCH Factura f ON p.id = f.producto.id
                                                GROUP BY p.id, p.nombre, p.precio
                                                HAVING sum(f.totalPagar) = max(f.totalPagar)
                                                ORDER BY sum(f.totalPagar) DESC, sum(f.cantidad) DESC, p.precio DESC
                                            """, DTOs.response.ProductoMasRecaudacionDTO.class);
        List<ProductoMasRecaudacionDTO> productos =  query.getResultList();
        em.getTransaction().commit();
        return productos;
    }

    public void deleteProducto(long id){

    }
}
