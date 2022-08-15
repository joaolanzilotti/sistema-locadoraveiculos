package controller;

import helper.Conexao;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
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

    public boolean removerFabricante() {

        boolean isValidRemover = false;

        EntityManager em = Conexao.getInstancia().getMysqlPU();

        List<Fabricante> listaFabricante = em.createQuery("select f from Fabricante f  where f.codigo=:codigoForm", Fabricante.class).setParameter("codigoForm", fabricante.getCodigo()).getResultList();
        System.out.println(listaFabricante);

        if (listaFabricante.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo Não Encontrado!");
        } else {

            em.getTransaction().begin();
            em.remove(listaFabricante.get(0));
            em.getTransaction().commit();
            //O em.frush diz que algum comando do entityManager Deve ser Executado agora! neste instante!
            //em.flush();
            JOptionPane.showMessageDialog(null, "Removido Com Sucesso!");
            isValidRemover = true;
        }

        return isValidRemover;

    }

    public boolean buscarFabricante() {

        boolean isValidBusca = false;

        if (fabricante.getCodigo().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Codigo Não Digitado", "AVISO", HEIGHT);
        } else
        {

            JOptionPane.showMessageDialog(null, "Fabricante Encontrado!", "AVISO", WIDTH);
            isValidBusca = true;

        }

        return isValidBusca;
    }
    
    public void EditarFabricante(){
        
        EntityManager em = Conexao.getInstancia().getMysqlPU();

        List<Fabricante> listaFabricantes = em.createQuery("select c from Fabricante c where c.codigo=:codigoForm ", Fabricante.class).setParameter("codigoForm", fabricante.getCodigo()).getResultList();
        System.out.println(listaFabricantes);

        if (fabricante.getCodigo().equals("") || fabricante.getNome().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos Obrigatórios!", "AVISO", HEIGHT);
        } else if (listaFabricantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Fabricante Não Encontrado!", "AVISO", HEIGHT);
        } else {

            for (Fabricante fabricante1 : listaFabricantes) {

                em.getTransaction().begin();

                fabricante1.setNome(fabricante.getNome());
                fabricante1.setCodigo(fabricante.getCodigo());

                em.getTransaction().commit();

            }

            JOptionPane.showMessageDialog(null, "Dados Alterado Com Sucesso!", "AVISO", WIDTH);

            em.close();
        }
    }
    
}
