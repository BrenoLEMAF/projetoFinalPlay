package models;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.GenericModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by breno on 07/04/16.
 */

@Entity
@Table(name = "usuario")
public class Usuario extends GenericModel{

    @Id
    @Column(name="id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_id_usuario_seq")
    @SequenceGenerator(name = "usuario_id_usuario_seq")
    public Integer id;

    @Column(name="nome")
    @Required
    public String nome;

    @Column(name="cpf")
    @Required
    @Unique
    public String cpf;

    @Column(name="data_nascimento")
    public Date dataNascimento;

    @Column(name="sexo")
    @Enumerated(EnumType.STRING)
    public Sexo sexo;

    @Required
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    public Cargo cargo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="rel_usuario_perfil", joinColumns=
            {@JoinColumn(name="id_usuario")}, inverseJoinColumns=
            {@JoinColumn(name="id_perfil")})
    public List<Perfil> perfis;

    @Column(name = "fl_ativo")
    public Boolean ativo;

    @Column(name = "data_cadastro")
    public Date dataCadastro;

    public Usuario(String nome, String cpf, Date dataNascimento, Sexo sexo, Cargo cargo, List<Perfil> perfisUsuario) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cargo = cargo;
        this.perfis = perfisUsuario;
        this.ativo = true;
        this.dataCadastro = new Date();
    }

    public void adiciona(){
        this.ativo = true;
        this.dataCadastro = new Date();
        List<Perfil> perfis = new ArrayList<Perfil>();
        for (Perfil perfil: this.perfis) {
            Perfil perfilBanco = Perfil.findById(perfil.id);
            perfis.add(perfilBanco);
        }
        this.perfis.clear();
        this.perfis.addAll(perfis);
        this.validateAndSave();
    }

    public void remove() {
        this.ativo = false;
        this.validateAndSave();
    }

    public void restaura() {
        this.ativo = true;
        this.validateAndSave();
    }

    public void edita(Usuario usuario){
        this.nome = usuario.nome;
        this.cpf = usuario.cpf;
        this.dataNascimento = usuario.dataNascimento;
        this.sexo = usuario.sexo;
        this.cargo = usuario.cargo;
        this.perfis = usuario.perfis;
        this.validateAndSave();
    }

    public static List<Usuario> listaTodos(){
        return Usuario.find("order by nome").fetch();
    }

    public static List<Usuario> listaPorNome(boolean ativo, String nome){
        if (ativo){
            return Usuario.find("lower(nome) like ? and ativo = ? order by nome", "%" + nome.toLowerCase() + "%", true).fetch();
        } else {
            return Usuario.find("lower(nome) like ? order by nome", "%" + nome.toLowerCase() + "%").fetch();
        }
    }

//    public static List<Usuario> listaPorPerfil(boolean ativo, Perfil... perfis){
//        if (ativo){
//            return Usuario.find("select distinct u from Usuario u join u.perfis as p " +
//                    "where u.ativo = ? and p in (:perfis) " +
//                    "group by u.id, u.nome, u.cpf, u.dataNascimento, u.sexo, u.cargo, u.situacao, u.dataCadastro " +
//                    "having count(p.id) = :size"
//            , true).bind("perfis", perfis).bind("size", perfis.length).fetch();
//        } else {
//            return Usuario.find("select distinct u from Usuario u join u.perfis as p " +
//                            "where p in (:perfis) " +
//                            "group by u.id, u.nome, u.cpf, u.dataNascimento, u.sexo, u.cargo, u.situacao, u.dataCadastro " +
//                            "having count(p.id) = :size"
//                    ).bind("perfis", perfis).bind("size", perfis.length).fetch();
//        }
//    }

    public static List<Usuario> listaPorPerfil(boolean ativo, Perfil perfil) {
        return Usuario.find("select distinct u from Usuario u join u.perfis as p where p.id = ? and u.ativo = ?", perfil.id, ativo).fetch();
    }


//    public static List<Usuario> listaPorPerfilSimplificado (Perfil perfil){
//        return Usuario.find("perfis in (:perfil)").setParameter("perfil",perfil).fetch();
//    }

    public static List<Usuario> listaPorCargo(boolean ativo, Cargo cargo){
        if (ativo) {
            return Usuario.find("cargo = ? and ativo = ? order by nome", cargo ,true).fetch();
        } else {
            return Usuario.find("cargo = ? order by nome", cargo).fetch();
        }
    }

}
