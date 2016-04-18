package controllers;

import models.Cargo;
import models.Perfil;
import models.Usuario;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by breno on 11/04/16.
 */
public class Usuarios extends DefaultController{

    public static void index() {
        List<Usuario> usuarios = Usuario.listaTodos();
        renderJSON(usuarios);
    }

    public static void listarNome(String nome){
        Usuario.listaPorNome(true, nome);
    }

    public static void listarPerfil(Perfil perfil){
        renderJSON(Usuario.listaPorPerfil(true, perfil));
    }

    public static void listarCargo(Cargo cargo){
        renderJSON(Usuario.listaPorCargo(true, cargo));
    }

    public static void salvar(Usuario usuario) {
        validation.valid(usuario);
        if (validation.hasErrors()) {
            renderText("Erro de validação");
        } else {
            usuario.adiciona();
            renderText("Usuário adicionado!");
        }
    }

    public static void apagar(Usuario usuario) {
        usuario.remove();
        renderText("Usuário apagado!");
    }


    public static void restaurar(Usuario usuario) {
        usuario.restaura();
        renderText("Usuário restaurado!");
    }

    public static void editar(Usuario usuario){
        validation.valid(usuario);
        if (validation.hasErrors()) {
            renderText("Erro de validação");
        } else {
            usuario.edita(usuario);
            renderText("Usuário modificado!");
        }
    }

    //TODO

}
