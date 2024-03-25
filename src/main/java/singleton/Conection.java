package singleton;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Conection {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static EntityManagerFactory getEMFInstance(){
        if(Conection.emf == null){
            Conection.emf = Persistence.createEntityManagerFactory("persistenceUnitName");
        }

        return Conection.emf;
    }

    public static EntityManager getEMInstance(){
        if(Conection.em == null){
            Conection.em = Conection.emf.createEntityManager();
        }

        return Conection.em;
    }
}
