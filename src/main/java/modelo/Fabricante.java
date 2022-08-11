
package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Essa Classe é minha entidadade

@Entity
public class Fabricante {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    private String nome;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Fabricante(String nome) {
        this.nome = nome;
    }

    public Fabricante(Long codigo) {
        this.codigo = codigo;
    }
    
    

    public Fabricante() {
    }
    
    
    
    
    
    
}
