package controllers;

import models.Cargo;

import java.util.List;

/**
 * Created by breno on 11/04/16.
 */
public class Cargos extends DefaultController {

    public static void index() {
        List<Cargo> cargos = Cargo.lista();
        renderJSON(cargos);
    }

    public static void salvar(Cargo cargo){
        validation.valid(cargo);
        if(validation.hasErrors()) {
            renderText("Erro de validação");
        }else {
            cargo.adiciona();
        }
        renderText("Cargo adicionado!");
    }

    public static void apagar(Cargo cargo){
        validation.valid(cargo);
        if(validation.hasErrors()) {
            renderText("Erro de validação");
        }else {
            cargo.remove();
            renderText("Cargo apagado!");
        }
    }

    public static void editar(Cargo cargo) {
        validation.valid(cargo);
        if (validation.hasErrors()) {
            renderText("Erro de validação");
        } else {
            cargo.edita(cargo);
            renderText("Cargo modificado!");
        }
    }

    //TODO

}
