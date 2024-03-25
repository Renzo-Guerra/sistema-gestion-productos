package factories;

import DAOs.ClienteDAOPostgreSQL;
import DAOs.FacturaDAOPostgreSQL;
import DAOs.ProductoDAOPostgreSQL;
import interfaces.*;

public class PostgreSQLFactory implements Factory {
    public PostgreSQLFactory() { }

    @Override
    public IClienteDAO getClienteDao() {
        return new ClienteDAOPostgreSQL();
    }

    @Override
    public IFacturaDAO getFacturaDao() {
        return new FacturaDAOPostgreSQL();
    }

    @Override
    public IProductoDAO getProductoDao() {
        return new ProductoDAOPostgreSQL();
    }
}
