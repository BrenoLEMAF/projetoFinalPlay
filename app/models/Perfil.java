package models;

import play.data.validation.Required;
import play.data.validation.Unique;
import play.db.jpa.GenericModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by breno on 07/04/16.
 */

@Entity
public class Perfil extends GenericModel{

    @Id
    @GeneratedValue
    private Integer id;

    @Required
    @Unique
    private String nome;

}
