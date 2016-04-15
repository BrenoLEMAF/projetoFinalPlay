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
@Table(name = "cargo")
public class Cargo extends GenericModel{

    @Id
    @Column(name="id_cargo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cargo_id_cargo_seq")
    @SequenceGenerator(name = "cargo_id_cargo_seq", sequenceName = "cargo_id_cargo_seq", allocationSize = 1)
    public Integer id;

    @Column(name="nome")
    @Required
    @Unique
    public String nome;

    public Cargo(String nome){
        this.nome = nome;
    }

    public void adiciona() {
        this.validateAndSave();
    }

    public void remove() {
        this.delete();
    }

    public void edita(Cargo cargo){
        this.nome = cargo.nome;
        this.save();
    }

    public static Cargo busca(String nome){
        return Cargo.find("byLikeNome", nome).first();
    }

    public static List<Cargo> lista(){
        return Cargo.find("order by nome asc").fetch();
    }

}
