package controller;


import helper.Conexao;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import modelo.Fabricante;

public class FabricanteController {

    Fabricante fabricante;


    public FabricanteController(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public void salvar() {
        
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("locadoraJ");
//        EntityManager em = emf.createEntityManager();
        
        System.out.println(fabricante.getNome());
       
        EntityManager em = Conexao.getInstancia().getMysqlPU();
        
        
        em.getTransaction().begin();
        em.persist(fabricante);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");
       
        
        
    }

}
