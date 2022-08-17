package controller;

import helper.Conexao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import modelo.Fabricante;
import modelo.ModeloCarro;

public class ModeloCarroController {

    ModeloCarro modeloCarro;

    public ModeloCarroController(ModeloCarro modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public void salvarModeloCarro() {

        System.out.println(modeloCarro.getDescricao());

        EntityManager em = Conexao.getInstancia().getMysqlPU();

        em.getTransaction().begin();
        em.persist(modeloCarro);
        em.getTransaction().commit();
        em.close();
        JOptionPane.showMessageDialog(null, "Modelo Cadastrado com Sucesso!");

    }

    public boolean removerModeloCarro() {

        boolean isValidRemover = false;
        
        EntityManager em = Conexao.getInstancia().getMysqlPU();

        List<ModeloCarro> listaModeloCarro = em.createQuery("select f from ModeloCarro f  where f.codigo=:codigoForm", ModeloCarro.class).setParameter("codigoForm", modeloCarro.getCodigo()).getResultList();
        System.out.println(listaModeloCarro);

        if (listaModeloCarro.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Codigo Não Encontrado!");
        } else {

            em.getTransaction().begin();
            em.remove(listaModeloCarro.get(0));
            em.getTransaction().commit();
            //O em.frush diz que algum comando do entityManager Deve ser Executado agora! neste instante!
            //em.flush();
           isValidRemover = true;
        }
        return isValidRemover;
    }

}
