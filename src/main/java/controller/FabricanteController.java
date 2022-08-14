package controller;

import GUI.TelaFabricantePesquisa;
import helper.Conexao;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import modelo.Fabricante;

public class FabricanteController {

    Fabricante fabricante;
    TelaFabricantePesquisa fabricantepesquisa;

    public FabricanteController(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public FabricanteController(TelaFabricantePesquisa fabricantepesquisa) {
        this.fabricantepesquisa = fabricantepesquisa;
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

    public void buscarFabricante() {

        EntityManager em = Conexao.getInstancia().getMysqlPU();

        System.out.println(fabricante.getCodigo());

        List<Fabricante> listaFabricantes = em.createQuery("select c from Fabricante c where c.codigo=:codigoForm", Fabricante.class).setParameter("codigoForm", fabricante.getCodigo()).getResultList();

        System.out.println(listaFabricantes);

        if (TelaFabricantePesquisa.caixaCodigoFabricante.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo Não Digitado", "AVISO", HEIGHT);
        } else {

            for (Fabricante fabricante1 : listaFabricantes) {

                JOptionPane.showMessageDialog(null, "Cliente Encontrado!", "AVISO", WIDTH);
                TelaFabricantePesquisa.caixaCodigoFabricante.setText(String.valueOf(fabricante1.getCodigo()));
                TelaFabricantePesquisa.caixaNomeFabricante.setText(fabricante1.getNome());

            }
            
        }  

    }
    
    public void EditarFabricante(){
        
        EntityManager em = Conexao.getInstancia().getMysqlPU();

        List<Fabricante> listaFabricantes = em.createQuery("select c from Fabricante c where c.codigo=:codigoForm ", Fabricante.class).setParameter("codigoForm", fabricante.getCodigo()).getResultList();
        System.out.println(listaFabricantes);

        if (TelaFabricantePesquisa.caixaCodigoFabricante.getText().equals("") || TelaFabricantePesquisa.caixaNomeFabricante.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Campos Obrigatórios!", "AVISO", HEIGHT);
        } else if (listaFabricantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cliente Não Encontrado!", "AVISO", HEIGHT);
        } else {

            for (Fabricante fabricante1 : listaFabricantes) {

                em.getTransaction().begin();

                fabricante1.setNome(TelaFabricantePesquisa.caixaNomeFabricante.getText());
                fabricante1.setCodigo(Long.parseLong(TelaFabricantePesquisa.caixaCodigoFabricante.getText()));

                em.getTransaction().commit();

            }

            JOptionPane.showMessageDialog(null, "Dados Alterado Com Sucesso!", "AVISO", WIDTH);

            em.close();
        }
    }
    
}
