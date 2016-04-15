package controllers;

import models.Perfil;
import models.Usuario;

import java.util.*;

/**
 * Created by breno on 11/04/16.
 */
public class Usuarios extends DefaultController{

    public static void index() {
        List<Usuario> usuarios = Usuario.listaTodos();
        renderJSON(usuarios);
    }

    public static void findUsuariosByPerfis(){
        Perfil perf1 = Perfil.busca("Perfil A");
        Usuario.listaPorPerfil(true,perf1);
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
