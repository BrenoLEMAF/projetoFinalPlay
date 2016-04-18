package models;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by breno on 07/04/16.
 */

@Entity
@Table(name = "perfil")
public class Perfil extends GenericModel{

    @Id
    @Column(name="id_perfil")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_id_perfil_seq")
    @SequenceGenerator(name = "perfil_id_perfil_seq")
    public Integer id;

    @Column(name="nome")
    @Required
    @Unique
    public String nome;

//    @ManyToMany(cascade=CascadeType.ALL, mappedBy="perfis")
//    public List<Usuario> Usuarios;

    public Perfil(String nome){
        this.nome = nome;
    }

    public void adiciona() {
        this.validateAndSave();
    }

    public void remove() {
        this.delete();
    }

    public void edita(Perfil perfil){
        this.nome = perfil.nome;
        this.save();
    }

    public static Perfil busca(String nome){
        return Perfil.find("byLikeNome", nome).first();
    }

    public static List<Perfil> lista(){
        return Perfil.find("order by nome asc").fetch();
    }

//    public List<Usuario> listaUsuarios() {
//        return this.Usuarios;
//    }
}
