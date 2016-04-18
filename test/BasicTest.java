import org.junit.*;
import java.util.*;

import play.test.*;
import models.*;

public class BasicTest extends UnitTest {


    @Before
    public void setup() {
        Fixtures.deleteDatabase();
        new Cargo("Presidente").save();
        new Cargo("Diretor").save();
        new Cargo("Gerente").save();
        new Cargo("Supervisor").save();
        new Cargo("Zoneador1").save();
        new Cargo("Zoneador2").save();

        Perfil perfil1 = new Perfil("PerfilA").save();
        Perfil perfil2 = new Perfil("PerfilB").save();
        Perfil perfil3 = new Perfil("PerfilC").save();
        Perfil perfil4 = new Perfil("PerfilD").save();

        List<Perfil> perfis1 = new HashSet<Perfil>();
        perfis1.add(perfil1);
        perfis1.add(perfil2);
        perfis1.add(perfil3);

        List<Perfil> perfis2 = new HashSet<Perfil>();
        perfis2.add(perfil3);
        perfis2.add(perfil4);

        List<Perfil> perfis3 = new HashSet<Perfil>();
        perfis3.add(perfil1);
        perfis3.add(perfil3);
        perfis3.add(perfil4);


        new Usuario("Breno", "123456789-54", new Date(), Sexo.M, Cargo.busca("Zoneador1"), perfis1).save();
        new Usuario("Raphael", "987654321-54", new Date(), Sexo.M, Cargo.busca("Zoneador1"), perfis2).save();
        new Usuario("Tetê", "654987321-12", new Date(), Sexo.F, Cargo.busca("Zoneador1"), perfis3).save();
        new Usuario("Alberto", "825369741-54", new Date(), Sexo.M, Cargo.busca("Zoneador1"), perfis2).save();
        new Usuario("Aarão", "132987454-54", new Date(), Sexo.M, Cargo.busca("Zoneador1"), perfis1).save();

    }

    @Test
    public void criarCargo() {

        assertEquals(6, Cargo.count());

        Cargo cargo1 = new Cargo("CEO");
        Cargo cargo2 = new Cargo("Faxineiro");
        Cargo cargo3 = new Cargo("Secretaria");
        Cargo cargo4 = new Cargo("Vigia");

        cargo1.adiciona();
        cargo2.adiciona();
        cargo3.adiciona();
        cargo4.adiciona();

        assertEquals(10, Cargo.count());

        List<Cargo> cargos = Cargo.find("byNome", "Presidente").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "Diretor").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "Gerente").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "Supervisor").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "CEO").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "Faxineiro").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "Secretaria").fetch();
        assertEquals(1, cargos.size());
        cargos = Cargo.find("byNome", "Vigia").fetch();
        assertEquals(1, cargos.size());

    }

    @Test
    public void removerCargo() {

        assertEquals(6, Cargo.count());

        Cargo cargoRem = Cargo.find("byNome", "Presidente").first();
        cargoRem.remove();
        assertEquals(5, Cargo.count());

        cargoRem = Cargo.find("byNome", "Supervisor").first();
        cargoRem.remove();
        assertEquals(4, Cargo.count());

        cargoRem = Cargo.find("byNome", "Diretor").first();
        cargoRem.remove();
        assertEquals(3, Cargo.count());

        cargoRem = Cargo.find("byNome", "Gerente").first();
        cargoRem.remove();
        assertEquals(2, Cargo.count());

    }

    @Test
    public void editarCargo() {

        assertEquals(6, Cargo.count());

        Cargo cargo = Cargo.find("byNome", "Presidente").first();
        cargo.edita("Faxineiro");
        List<Cargo> cargos = Cargo.find("byNome", "Faxineiro").fetch();
        assertEquals(1, cargos.size());
        assertEquals(6, Cargo.count());

        cargo = Cargo.find("byNome", "Faxineiro").first();
        cargo.edita("Presidente");
        cargos = Cargo.find("byNome", "Presidente").fetch();
        assertEquals(1, cargos.size());
        assertEquals(6, Cargo.count());

        cargo = Cargo.find("byNome", "Diretor").first();
        cargo.edita("Vigia");
        cargos = Cargo.find("byNome", "Vigia").fetch();
        assertEquals(1, cargos.size());
        assertEquals(6, Cargo.count());

        cargo = Cargo.find("byNome", "Supervisor").first();
        cargo.edita("CEO");
        cargos = Cargo.find("byNome", "CEO").fetch();
        assertEquals(1, cargos.size());
        assertEquals(6, Cargo.count());

    }

    @Test
    public void buscarCargo() {

        assertEquals(6, Cargo.count());

        Cargo cargo = Cargo.busca("Presidente");
        assertNotNull(cargo);
        cargo = Cargo.busca("Diretor");
        assertNotNull(cargo);
        cargo = Cargo.busca("Gerente");
        assertNotNull(cargo);
        cargo = Cargo.busca("Supervisor");
        assertNotNull(cargo);

    }

    @Test
    public void listarCargos() {

        assertEquals(6, Cargo.count());

        List<Cargo> cargos = Cargo.lista();
        assertEquals("Diretor", cargos.get(0).nome);
        assertEquals("Gerente", cargos.get(1).nome);
        assertEquals("Presidente", cargos.get(2).nome);
        assertEquals("Supervisor", cargos.get(3).nome);

        for (Cargo cargo : cargos) {
            System.out.println(cargo.nome);
        }

    }

    @Test
    public void criarPerfil() {

        assertEquals(4, Perfil.count());

        Perfil perfil1 = new Perfil("PerfilE");
        Perfil perfil2 = new Perfil("PerfilF");
        Perfil perfil3 = new Perfil("PerfilG");
        Perfil perfil4 = new Perfil("PerfilH");

        perfil1.adiciona();
        perfil2.adiciona();
        perfil3.adiciona();
        perfil4.adiciona();

        assertEquals(8, Perfil.count());

        List<Perfil> perfis = Perfil.find("byNome", "PerfilA").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilB").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilC").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilD").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilE").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilF").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilG").fetch();
        assertEquals(1, perfis.size());
        perfis = Perfil.find("byNome", "PerfilH").fetch();
        assertEquals(1, perfis.size());

    }

    @Test
    public void removerPerfil() {

        assertEquals(4, Perfil.count());

        new Perfil("PerfilE").save();
        new Perfil("PerfilF").save();
        new Perfil("PerfilG").save();
        new Perfil("PerfilH").save();

        assertEquals(8, Perfil.count());

        Perfil perfil = Perfil.find("byNome", "PerfilE").first();
        perfil.remove();
        assertEquals(7, Perfil.count());

        perfil = Perfil.find("byNome", "PerfilG").first();
        perfil.remove();
        assertEquals(6, Perfil.count());

        perfil = Perfil.find("byNome", "PerfilF").first();
        perfil.remove();
        assertEquals(5, Perfil.count());

        perfil = Perfil.find("byNome", "PerfilH").first();
        perfil.remove();
        assertEquals(4, Perfil.count());

    }

    @Test
    public void editarPerfil() {

        assertEquals(4, Perfil.count());

        Perfil perfil = Perfil.find("byNome", "PerfilA").first();
        perfil.edita("PerfilE");
        List<Perfil> perfis = Perfil.find("byNome", "PerfilE").fetch();
        assertEquals(1, perfis.size());
        assertEquals(4, Perfil.count());

        perfil = Perfil.find("byNome", "PerfilE").first();
        perfil.edita("PerfilA");
        perfis = Perfil.find("byNome", "PerfilA").fetch();
        assertEquals(1, perfis.size());
        assertEquals(4, Perfil.count());

        perfil = Perfil.find("byNome", "PerfilB").first();
        perfil.edita("PerfilF");
        perfis = Perfil.find("byNome", "PerfilF").fetch();
        assertEquals(1, perfis.size());
        assertEquals(4, Perfil.count());

        perfil = Perfil.find("byNome", "PerfilC").first();
        perfil.edita("PerfilG");
        perfis = Perfil.find("byNome", "PerfilG").fetch();
        assertEquals(1, perfis.size());
        assertEquals(4, Perfil.count());

    }

    @Test
    public void buscarPerfil() {

        assertEquals(4, Perfil.count());

        Perfil perfil = Perfil.busca("PerfilA");
        assertNotNull(perfil);
        perfil = Perfil.busca("PerfilB");
        assertNotNull(perfil);
        perfil = Perfil.busca("PerfilC");
        assertNotNull(perfil);
        perfil = Perfil.busca("PerfilD");
        assertNotNull(perfil);

    }

    @Test
    public void listarPerfis() {

        assertEquals(4, Perfil.count());

        List<Perfil> perfis = Perfil.lista();
        assertEquals("PerfilA", perfis.get(0).nome);
        assertEquals("PerfilB", perfis.get(1).nome);
        assertEquals("PerfilC", perfis.get(2).nome);
        assertEquals("PerfilD", perfis.get(3).nome);

        for (Perfil perfil : perfis) {
            System.out.println(perfil.nome);
        }

    }

    @Test
    public void criarUsuarios(){
        assertEquals(5, Usuario.count());

        Usuario usuario1 = new Usuario("Fulano", "111111111-11", new Date(), Sexo.M, Cargo.busca("Zoneador1"), null);
        Usuario usuario2 = new Usuario("Fulana", "222222222-22", new Date(), Sexo.F, Cargo.busca("Zoneador1"), null);
        Usuario usuario3 = new Usuario("Ciclano", "333333333-33", new Date(), Sexo.M, Cargo.busca("Zoneador1"), null);
        Usuario usuario4 = new Usuario("Ciclana", "444444444-44", new Date(), Sexo.F, Cargo.busca("Zoneador1"), null);

        usuario1.adiciona();
        usuario2.adiciona();
        usuario3.adiciona();
        usuario4.adiciona();

        assertEquals(9, Usuario.count());

        List<Usuario> usuarios = Usuario.find("byNome", "Breno").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Raphael").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Tetê").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Alberto").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Aarão").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Fulano").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Fulana").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Ciclano").fetch();
        assertEquals(1, usuarios.size());
        usuarios = Usuario.find("byNome", "Ciclana").fetch();
        assertEquals(1, usuarios.size());
    }

    @Test
    public void removerUsuarios(){
        assertEquals(5, Usuario.count());

        Usuario usuario1 = new Usuario("Fulano", "111111111-11", new Date(), Sexo.M, Cargo.busca("Zoneador1"), null);
        Usuario usuario2 = new Usuario("Fulana", "222222222-22", new Date(), Sexo.F, Cargo.busca("Zoneador1"), null);
        Usuario usuario3 = new Usuario("Ciclano", "333333333-33", new Date(), Sexo.M, Cargo.busca("Zoneador1"), null);
        Usuario usuario4 = new Usuario("Ciclana", "444444444-44", new Date(), Sexo.F, Cargo.busca("Zoneador1"), null);

        usuario1.adiciona();
        usuario2.adiciona();
        usuario3.adiciona();
        usuario4.adiciona();

        assertEquals(9, Usuario.count());

        Usuario usuario = Usuario.find("byNome", "Fulano").first();
        usuario.remove();
        assertEquals(8, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(1, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Fulana").first();
        usuario.remove();
        assertEquals(7, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(2, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Ciclano").first();
        usuario.remove();
        assertEquals(6, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(3, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Ciclana").first();
        usuario.remove();
        assertEquals(5, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(4, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

    }

    @Test
    public void restaurarUsuarios(){
        assertEquals(5, Usuario.count());

        List<Usuario> usuarios = Usuario.findAll();
        for (Usuario usuario: usuarios) {
            usuario.remove();
        }
        assertEquals(0, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());

        Usuario usuario = Usuario.find("byNome", "Breno").first();
        usuario.restaura();
        assertEquals(1, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(4, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Raphael").first();
        usuario.restaura();
        assertEquals(2, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(3, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Tetê").first();
        usuario.restaura();
        assertEquals(3, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(2, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Alberto").first();
        usuario.restaura();
        assertEquals(4, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(1, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

        usuario = Usuario.find("byNome", "Aarão").first();
        usuario.restaura();
        assertEquals(5, Usuario.find("bySituacao", Situacao.ATIVO).fetch().size());
        assertEquals(0, Usuario.find("bySituacao", Situacao.INATIVO).fetch().size());

    }

    @Test
    public void editarUsuarios(){
        assertEquals(5, Usuario.count());
        assertEquals(0, Usuario.find("byNome", "NomeGenerico").fetch().size());

        List<Usuario> usuarios = Usuario.findAll();
        for (Usuario usuario: usuarios) {
            usuario.edita("NomeGenerico", usuario.cpf, usuario.dataNascimento, usuario.sexo, usuario.cargo, usuario.perfis);
        }

        assertEquals(5, Usuario.count());
        assertEquals(5, Usuario.find("byNome", "NomeGenerico").fetch().size());
    }

    @Test
    public void listarUsuariosPorNome(){
        assertEquals(5, Usuario.count());

        List<Usuario> usuarios;
        usuarios = Usuario.listaPorNome(true, "e");
        assertEquals(4, usuarios.size());
        usuarios = Usuario.listaPorNome(false, "e");
        assertEquals(4, usuarios.size());
        usuarios = Usuario.listaPorNome(true, "r");
        assertEquals(4, usuarios.size());
        usuarios = Usuario.listaPorNome(false, "r");
        assertEquals(4, usuarios.size());
        usuarios = Usuario.listaPorNome(true, "l");
        assertEquals(2, usuarios.size());
        usuarios = Usuario.listaPorNome(false, "l");
        assertEquals(2, usuarios.size());

    }

    @Test
    public void listarUsuariosPorCargo(){
        assertEquals(5, Usuario.count());

        new Usuario("Fulano", "111111111-11", new Date(), Sexo.M, Cargo.busca("Zoneador2"), null).save();
        new Usuario("Fulana", "222222222-22", new Date(), Sexo.F, Cargo.busca("Zoneador2"), null).save();

        Cargo cargo = Cargo.busca("Zoneador1");
        List<Usuario> usuarios;
        usuarios = Usuario.listaPorCargo(true, cargo);
        assertEquals(5, usuarios.size());
        usuarios = Usuario.listaPorCargo(false, cargo);
        assertEquals(5, usuarios.size());

        cargo = Cargo.busca("Zoneador2");
        usuarios = Usuario.listaPorCargo(true, cargo);
        assertEquals(2, usuarios.size());
        usuarios = Usuario.listaPorCargo(false, cargo);
        assertEquals(2, usuarios.size());

        cargo = Cargo.busca("Presidente");
        usuarios = Usuario.listaPorCargo(true, cargo);
        assertEquals(0, usuarios.size());
        usuarios = Usuario.listaPorCargo(false, cargo);
        assertEquals(0, usuarios.size());

    }

    @Test
    public void listarUsuariosPorPerfil(){
        assertEquals(5, Usuario.count());

        List<Usuario> usuarios;

        Perfil perA = Perfil.busca("PerfilA");
        Perfil perB = Perfil.busca("PerfilB");
        Perfil perC = Perfil.busca("PerfilC");
        Perfil perD = Perfil.busca("PerfilD");

        usuarios = Usuario.listaPorPerfil(true, perA);
        assertEquals(3, usuarios.size());
        usuarios = Usuario.listaPorPerfil(false, perA);
        assertEquals(3, usuarios.size());

        usuarios = Usuario.listaPorPerfil(true, perB);
        assertEquals(2, usuarios.size());
        usuarios = Usuario.listaPorPerfil(false, perB);
        assertEquals(2, usuarios.size());

        usuarios = Usuario.listaPorPerfil(true, perC);
        assertEquals(5, usuarios.size());
        usuarios = Usuario.listaPorPerfil(false, perC);
        assertEquals(5, usuarios.size());

        usuarios = Usuario.listaPorPerfil(true, perD);
        assertEquals(3, usuarios.size());
        usuarios = Usuario.listaPorPerfil(false, perD);
        assertEquals(3, usuarios.size());

        usuarios = Usuario.listaPorPerfil(true, perC, perD);
        assertEquals(3, usuarios.size());
        usuarios = Usuario.listaPorPerfil(false, perC, perD);
        assertEquals(3, usuarios.size());

        usuarios = Usuario.listaPorPerfil(true, perA, perC, perD);
        assertEquals(1, usuarios.size());
        usuarios = Usuario.listaPorPerfil(false, perA, perC, perD);
        assertEquals(1, usuarios.size());

    }
}
