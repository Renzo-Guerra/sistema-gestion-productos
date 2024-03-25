# ABSTRACT FACTORY

Sistema en el cual el usuario a travez de **DAOS** los cuales son generados a traves de un **Abstract Factory** permiten 
al usuario una facilidad a la hora de una posible migración o de que el mismo sistema deba correr para 2 clientes, 
los cuales cada uno desea mantener la persistencia de datos de formas diferentes (una base de datos o una hoja excel).

Se implementó un **Singleton** en el EntityManagerFactory, de esta manera cada DAO utiliza siempre el mismo EntityManager.
Para que este funcione correctamente, se debe especificar el nombre del _persistenceUnitName_a utilizar del 
persistence.xml. Ademas, al inicio del programa se debe generar la unica instancia del EntityManagerFactory.


## ClienteDAO Metodos
- [Agregar Cliente](#agregar-cliente)
- [Obtener Clientes](#obtener-clientes)
- [Obtener cliente en base a id](#obtener-entidad-cliente-en-base-a-id)
- [Obtener cliente en base a id con sus facturas](#obtener-cliente-en-base-a-id-con-sus-facturas)
- [Obtener clientes que mas han gastado](#obtener-clientes-que-mas-han-gastado)

### Agregar cliente

```` Java
    void addCliente(AgregarClienteDTO cliente);
````

**AgregarClienteDTO**(long dniCliente, String nombre, String email).

--- 

### Obtener clientes
Se obtienen todos los clientes, mostrando su informacion básica.
```` Java
    public List<ClienteDTO> getClientes();
````

**ClienteDTO**(long dniCliente, String nombre, String email)

--- 

### Obtener entidad cliente en base a id
Se obtiene un cliente en particular en base a su id (dni).
```` Java
public Cliente getCliente(long dni);
````
Se devuelve la Entidad como tal en vez de un DTO.

--- 

### Obtener cliente en base a id con sus facturas
Se obtiene un cliente en particular en base a su id (dni) con su informacion basica mas sus facturas.
```` Java
    public ClienteFacturaDTO getClienteConFactura(long dni);
````
**ClienteFacturaDTO**(long dniCliente, String nombre, String email, List<Factura> facturas)

--- 

### Obtener clientes que mas han gastado
Devuelve una lista con aquellos clientes que en terminos de gastos, igualen al mayor de todos.
```` Java
        public List<ClienteTotalGastadoDto> getClientesOrdenadosPorMayorFacturacion();
````
**ClienteTotalGastadoDto**(long idCliente, String nombre, String email, double totalGastado)

## ProductoDAO Metodos
- [Agregar Producto](#agregar-producto)
- [Obtener Productos](#obtener-productos)
- [Obtener producto en base a id](#obtener-producto-en-base-a-id)
- [Obtener productos mayor recaudacion](#obtener-productos-mayor-recaudacion)

### Agregar producto

```` Java
    void addProducto(AgregarProductoDTO producto);
````

**AgregarProductoDTO**(String nombre, double precio)

--- 

### Obtener productos
Se obtienen todos los productos, mostrando su informacion básica.
```` Java
    public List<ProductoDTO> getProductos();
````

**ProductoDTO**(long id, String nombre, double precio)

--- 

### Obtener producto en base a id
Se obtiene un producto en particular en base a su id.
```` Java
    public Producto getProducto(long id);
````
Se devuelve la Entidad como tal en vez de un DTO.

--- 

### Obtener productos mayor recaudacion
Devuelve una lista con los productos tengan la mayor recaudacion historica, 
se devuelve una lista en caso de que haya un empate.
```` Java
    public List<ProductoMasRecaudacionDTO> getProductoConMayorRecaudacion();
````
**ProductoMasRecaudacionDTO**(long idProducto, String nombre, double precio, long unidadesVendidas, double totalRecaudado)

## FacturaDAO Metodos
- [Agregar Factura](#agregar-factura)
- [Obtener facturas detalladas](#obtener-facturas-detalladas)
- [Obtener facturas resumidas](#obtener-facturas-resumidas)
- [Obtener factura resumida en base al id](#obtener-factura-resumida-en-base-al-id)
- [Obtener factura detallada en base a id](#obtener-factura-detallada-en-base-a-id)

### Agregar factura
Para realizar la insercion, al metodo se le aplica inyeccion de dependencia de los DAOs de cliete y producto, 
ya que internamente necesita obtener los Objetos Ciente y Producto.
```` Java
    public void addFactura(IClienteDAO clienteDAO, IProductoDAO productoDAO, @Valid AgregarFacturaDTO factura);
````

**AgregarFacturaDTO**(long idProducto, long idCliente, int cantidad).

--- 

### Obtener facturas detalladas
Devuelve todas las facturas con el producto comprado y quien realizó la compra.
```` Java
    public List<FacturaDetailedDTO> getFacturasDetailedInfo();
````

**FacturaDetailedDTO**(long idFactura, Producto producto, Cliente cliente, int cantidad, double totalPagar)

--- 

### Obtener facturas resumidas
Devuelve todas las facturas con el id del producto comprado y el id de quien realizó la compra.
```` Java
    public List<FacturaDTO> getFacturas();
````
**FacturaDTO**(long idFactura, long idProducto, long idCliente, int cantidad, double totalPagar)

--- 

### Obtener factura resumida en base al id
Se obtiene una factura en particular en base a su id (id) con el id del producto comprado y el id de quien realizó la compra.
```` Java
    public FacturaDTO getFactura(long id);
````
**FacturaDTO**(long idFactura, long idProducto, long idCliente, int cantidad, double totalPagar)

--- 

### Obtener factura detallada en base a id
Se obtiene una factura en particular en base a su id (id) con la inforamcion del producto comprado y el cliente quien realizó la compra.
```` Java
    public FacturaDetailedDTO getFacturaDetailedInfo(long id);
````
**FacturaDetailedDTO**(long idFactura, Producto producto, Cliente cliente, int cantidad, double totalPagar)
