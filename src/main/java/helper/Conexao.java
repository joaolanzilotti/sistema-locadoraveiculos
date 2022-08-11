package helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Conexao {

    private static Conexao instancia;

    public static Conexao getInstancia() {
        if (instancia == null) {
            instancia = new Conexao();
        }
        return instancia;
    }

    private Conexao() {
    }

    public EntityManager getMysqlPU() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraJ");
        EntityManager em = emf.createEntityManager();
        return em;
    }


}
