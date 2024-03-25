package interfaces;

import DTOs.request.AgregarProductoDTO;
import DTOs.response.ProductoDTO;
import DTOs.response.ProductoMasRecaudacionDTO;
import entidades.Producto;

import java.util.List;

public interface IProductoDAO {
    void addProducto(AgregarProductoDTO producto);
    List<ProductoDTO> getProductos();
    Producto getProducto(long id);

    /**
     * Calcula los productos con la mayor recaudacion
     * (siendo "mayor recaudacion" = cantidadProductosVendidos * precioProducto).
     * @return List<ProductoMasRecaudacionDTO> de los productos con la mayor recaudacion.
     */
    List<ProductoMasRecaudacionDTO> getProductoConMayorRecaudacion();
    void deleteProducto(long id);
}
