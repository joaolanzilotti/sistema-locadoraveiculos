
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ModeloCarro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    private String descricao;
    
    //Para Mapear é necessario puxar a classe entidade!
    //Muitos ModeloCarro para Um Fabricante
    @ManyToOne
    private Fabricante fabricante;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public ModeloCarro(Long codigo, String descricao, Fabricante fabricante) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.fabricante = fabricante;
    }

    public ModeloCarro(String descricao) {
        this.descricao = descricao;
    }

    public ModeloCarro(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public ModeloCarro(Long codigo) {
        this.codigo = codigo;
    }

    public ModeloCarro() {
    }
    
    
    
    
    
}
