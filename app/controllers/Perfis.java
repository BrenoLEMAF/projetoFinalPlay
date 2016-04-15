package controllers;

import models.Perfil;

import java.util.List;

/**
 * Created by breno on 11/04/16.
 */
public class Perfis extends DefaultController{

    public static void index() {
        List<Perfil> perfis = Perfil.lista();
        renderJSON(perfis);
    }

    public static void salvar(Perfil perfil){
        validation.valid(perfil);
        if(validation.hasErrors()) {
            renderText("Erro de validação");
        }else {
            perfil.adiciona();
            renderText("Perfil adicionado!");
        }
    }

    public static void apagar(Perfil perfil){
        validation.valid(perfil);
        if(validation.hasErrors()) {
            renderText("Erro de validação");
        }else {
            perfil.remove();
            renderText("Perfil apagado!");
        }
    }

    public static void editar(Perfil perfil){
        validation.valid(perfil);
        if(validation.hasErrors()) {
            renderText("Erro de validação");
        }else {
            perfil.edita(perfil);
            renderText("Perfil modificado!");
        }
    }

    //TODO

}
