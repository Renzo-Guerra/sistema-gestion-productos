import DTOs.request.AgregarClienteDTO;
import DTOs.request.AgregarFacturaDTO;
import DTOs.request.AgregarProductoDTO;
import DTOs.response.ClienteDTO;
import DTOs.response.FacturaDTO;
import DTOs.response.FacturaDetailedDTO;
import DTOs.response.ProductoDTO;
import factories.PostgreSQLFactory;
import interfaces.Factory;
import singleton.Conection;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Se instancia por unica vez el EntityManagerFactory
        // para que Conection pueda instanciar un EntityManager por unica vez.

        // NOTA: (En Conection hay que configurar el "persistenceUnitName"
        // del META-INF/persistence.xml dependiendo de la factory a utilizar en la ejecucion)
        Conection.getEMFInstance();

        // Instanciamos la Factory deseada
        Factory factory = new PostgreSQLFactory();

        // Realizamos las operaciones que deseemos

        // Creamos los clientes
        factory.getClienteDao().addCliente(new AgregarClienteDTO(32555666, "Miguel", "miguelsanchez@gmail.com"));
        factory.getClienteDao().addCliente(new AgregarClienteDTO(13111222, "Lucia", "luciafigueroa@hotmail.com"));
        factory.getClienteDao().addCliente(new AgregarClienteDTO(16777888, "Juan", "juanmecanico@gmail.com"));
        List<ClienteDTO> clientes = factory.getClienteDao().getClientes();
        System.out.println(clientes);

        // Creamos los productos
        factory.getProductoDao().addProducto(new AgregarProductoDTO("Chomba Hombre Algodon Peinado", 8250));
        factory.getProductoDao().addProducto(new AgregarProductoDTO("Libro Hábitos Atómicos - James Clear", 14702));
        factory.getProductoDao().addProducto(new AgregarProductoDTO("Taladro Percutor 560w Tm500-ar Black+decker", 53163));
        factory.getProductoDao().addProducto(new AgregarProductoDTO("Auriculares in-ear gamer inalámbricos Bluetooth", 5900));
        factory.getProductoDao().addProducto(new AgregarProductoDTO("Cargador Rapido Dual Cable Micro Usb Para Samsung Motorola", 7139));
        factory.getProductoDao().addProducto(new AgregarProductoDTO("Film Vidrio Hidrogel Protector Mate Para Todos Los Samsung A", 5094));
        List<ProductoDTO> productos = factory.getProductoDao().getProductos();
        System.out.println(productos);

        // Creamos las facturas
        factory.getFacturaDao().addFactura(factory.getClienteDao(), factory.getProductoDao(), new AgregarFacturaDTO(1, 32555666, 3));
        factory.getFacturaDao().addFactura(factory.getClienteDao(), factory.getProductoDao(), new AgregarFacturaDTO(2, 13111222, 2));
        factory.getFacturaDao().addFactura(factory.getClienteDao(), factory.getProductoDao(), new AgregarFacturaDTO(1, 16777888, 4));

        // TODO: Validaciones con algun packete a la hora de hacer un ""POST"".
        factory.getFacturaDao().addFactura(factory.getClienteDao(), factory.getProductoDao(), new AgregarFacturaDTO(1, 16777888, -10));

        // Mostramos las facturas de forma "detallada"
        List<FacturaDetailedDTO> facturasDetailed = factory.getFacturaDao().getFacturasDetailedInfo();
        System.out.println(facturasDetailed);
        // Mostramos las facturas de forma "corta"
        List<FacturaDTO> facturasShortInfo = factory.getFacturaDao().getFacturas();
        System.out.println(facturasShortInfo);

        // Mostramos una factura de forma individual
        System.out.println(factory.getFacturaDao().getFactura(2));

        System.out.println("Lista de productos con la mayor recaudacion: ");
        System.out.println(factory.getProductoDao().getProductoConMayorRecaudacion());

        System.out.println("Lista de clientes ordenados por total gastado: ");
        System.out.println(factory.getClienteDao().getClientesOrdenadosPorMayorFacturacion());
        // Intentamos mostrar una factura con un id invalido...
        System.out.println(factory.getFacturaDao().getFactura(123141));

        // Por ultimo, cerramos las instancias de tanto el EntityManager como la del EntityManagerFactory.
        Conection.getEMInstance().close();
        Conection.getEMFInstance().close();
    }
}
