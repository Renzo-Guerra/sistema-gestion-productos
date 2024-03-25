package interfaces;


public interface Factory {

    IClienteDAO getClienteDao();
    IFacturaDAO getFacturaDao();
    IProductoDAO getProductoDao();
}

