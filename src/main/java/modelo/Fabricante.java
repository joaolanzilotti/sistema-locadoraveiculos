
package modelo;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Essa Classe é minha entidadade
@Entity
public class Fabricante {

    //O @Column(name="nome") -> é utilziado para alterar o nome no banco de dados , mas aqui no java continua o mesmo!
    //@Column(name="Id")
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

    public Fabricante(Long codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public Fabricante() {
    }
 

}
