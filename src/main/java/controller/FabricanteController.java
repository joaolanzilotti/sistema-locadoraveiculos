package controller;


import helper.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import modelo.Fabricante;

public class FabricanteController {

    Fabricante fabricante;


    public FabricanteController(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    
    public void salvarFabricante() {
        
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

    public boolean removerFabricante(){
        
            boolean isValidRemover = false;
    
        EntityManager em = Conexao.getInstancia().getMysqlPU();
        
        List<Fabricante> listaFabricante = em.createQuery("select f from Fabricante f  where f.codigo=:codigoForm", Fabricante.class).setParameter("codigoForm", fabricante.getCodigo()).getResultList();
        System.out.println(listaFabricante);
        
        if(listaFabricante.isEmpty()){JOptionPane.showMessageDialog(null, "Codigo Não Encontrado!");}
        
        else{
        em.getTransaction().begin();
        em.remove(listaFabricante.get(0));
        em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "Removido Com Sucesso!");
        isValidRemover = true;
        }
        
        
        return isValidRemover;
        
    }

    
}
