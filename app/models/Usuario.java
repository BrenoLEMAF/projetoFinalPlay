package models;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by breno on 07/04/16.
 */

@Entity
public class Usuario extends GenericModel{

    @Id
    @GeneratedValue
    public Integer id;

    @Required
    public String nome;

    @Required
    @Unique
    public String cpf;

    public Date dataNascimento;

    @Enumerated(EnumType.STRING)
    public Sexo sexo;

    @Required
    @ManyToOne(cascade = CascadeType.ALL)
    public Cargo cargo;

    @ManyToMany(cascade = CascadeType.PERSIST)
    public Set<Perfil> perfisUsuario;

    @Enumerated(EnumType.STRING)
    public Situacao situacao;

    public Date dataCadastro;

}
