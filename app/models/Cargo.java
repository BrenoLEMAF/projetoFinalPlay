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
public class Cargo extends GenericModel{

    @Id
    @GeneratedValue
    public Integer id;

    @Required
    @Unique
    public String nome;

}
