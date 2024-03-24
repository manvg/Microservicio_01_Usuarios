package com.usuarios.usuarios;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.RestController;
import com.usuarios.usuarios.Clases.InfoUsuariosPorPerfil;
import com.usuarios.usuarios.Clases.Perfil;
import com.usuarios.usuarios.Clases.Usuario;
import com.usuarios.usuarios.Excepciones.CustomException;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController 
public class UsuarioController {
    //#region Carga de datos
    List<Usuario> lstUsuarios = new ArrayList<>();
    List<Perfil> lstPerfiles = new ArrayList<>();

    public UsuarioController(){
        //Cargar Perfiles
        lstPerfiles.add(new Perfil(1, "Administrador"));
        lstPerfiles.add(new Perfil(2, "Contabilidad"));
        lstPerfiles.add(new Perfil(3, "Ventas"));
        lstPerfiles.add(new Perfil(4, "Cliente"));
        //Cargar fechas de ingresos para los usuarios
        LocalDate fechaIngresoU1 =LocalDate.of( 2024, 1, 30);
        LocalDate fechaIngresoU2 =LocalDate.of( 2024, 2, 15);
        LocalDate fechaIngresoU3 =LocalDate.of( 2024, 3, 1);
        LocalDate fechaIngresoU4 =LocalDate.of( 2024, 3, 20);
        LocalDate fechaIngresoU5 =LocalDate.of( 2024, 3, 21);
        LocalDate fechaIngresoU6 =LocalDate.of( 2024, 3, 22);
        //Agregar usuarios a las listas
        lstUsuarios.add(new Usuario(1, "Manuel", "Valdés", "Guerra", "mvaldes@duoc.cl", "123456", 984576321, "", fechaIngresoU1, (new Perfil(1, "Administrador")), true));
        lstUsuarios.add(new Usuario(2, "Roberto", "Torres", "Martinez", "mmartinez@duoc.cl", "123456", 84568542, "", fechaIngresoU2, (new Perfil(2, "Contabilidad")), true));
        lstUsuarios.add(new Usuario(3, "Silvia", "Acosta", "Lopez", "sacosta@duoc.cl", "123456", 84575325, "", fechaIngresoU3, (new Perfil(3, "Ventas")), true));
        lstUsuarios.add(new Usuario(4, "María ", "Aguirre", "Torres", "jgimenez@duoc.cl", "123456", 84575325, "", fechaIngresoU4, (new Perfil(3, "Ventas")), false));
        lstUsuarios.add(new Usuario(5, "Carlos ", "Herrera", "Diaz", "cherrera@duoc.cl", "123456", 75468598, "", fechaIngresoU5, (new Perfil(4, "Cliente")), false));
        lstUsuarios.add(new Usuario(6, "Roberto", "Torres", "Alvarez", "rtorres@duoc.cl", "123456", 84568542, "", fechaIngresoU6, (new Perfil(4, "Cliente")), false));
    }
    //#endregion
    
    //Obtener lista de todos los usuarios
    @GetMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        return lstUsuarios;
    }
    //Obtener usuarios por idUsuario
    @GetMapping("/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable int id){
        for(Usuario usuario : lstUsuarios){
            if (usuario.getIdUsuario() == id) {
                return usuario;
            }
        }

        throw new CustomException("El usuario no existe.");
    }
    //Obtener lista de usuarios por su idPerfil
    @GetMapping("/usuarios-by-id-perfil/{idPerfil}")
    public List<Usuario> getUsuarioByIdPerfil(@PathVariable int idPerfil){
        List<Usuario> lstUsuariosRetorno = new ArrayList<>();
        for(Usuario usuario : lstUsuarios){
            if (usuario.getPerfil().getIdPerfil() == idPerfil) {
                lstUsuariosRetorno.add(usuario);
            }
        }
        if(!lstUsuariosRetorno.isEmpty()){
            return lstUsuariosRetorno;
        }else{
            throw new CustomException("No hay resultados para el Perfil ingresado.");
        }
    }
    //Obtener lista de usuarios activos
    @GetMapping("/usuarios-activos")
    public List<Usuario> getUsuariosActivos(){
        List<Usuario> lstUsuariosActivos = new ArrayList<>();
        for(Usuario usuario : lstUsuarios){
            if (usuario.estaActivo()) {
                lstUsuariosActivos.add(usuario);
            }
        }

        if(!lstUsuariosActivos.isEmpty()){
            return lstUsuariosActivos;
        }else{
            throw new CustomException("No existen usuarios activos.");
        }
    }
    //Obtener lista de usuarios según rango de fecha de creación
    @GetMapping("/usuarios/rango-fecha/{fechaDesde}/{fechaHasta}")
    public List<Usuario> getUsuariosRangoFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta){
        if (fechaHasta.isBefore(fechaDesde)) {
            throw new CustomException("Rango de fecha no válido.");
        }
        List<Usuario> lstUsuariosRangoFecha = new ArrayList<>();
        for(Usuario usuario : lstUsuarios){
            if (!usuario.getFechaCreacion().isBefore(fechaDesde) && !usuario.getFechaCreacion().isAfter(fechaHasta)) {
                    lstUsuariosRangoFecha.add(usuario);
                }
            }
    
        if(!lstUsuariosRangoFecha.isEmpty()){
            return lstUsuariosRangoFecha;
        }else{
            throw new CustomException("No existen usuarios creados en el rango de fecha ingresado.");
        }
    }

    //Obtener lista con información de la cantidad de usuarios por cada perfil
    @GetMapping("/cantidad-usuarios")
    public List<InfoUsuariosPorPerfil> getCantidasUsuariosPorDeparamento(){
        List<InfoUsuariosPorPerfil> lstInfoUsuariosPorPerfil= new ArrayList<>();
        int contadorUsuarios = 0;

        for (Perfil perfil : lstPerfiles){
            for(Usuario usuario : lstUsuarios){
                if (usuario.getPerfil().getIdPerfil() == perfil.getIdPerfil()) {
                    contadorUsuarios++;
                }
            }
            lstInfoUsuariosPorPerfil.add(new InfoUsuariosPorPerfil(perfil.getNombre(), contadorUsuarios));
            contadorUsuarios = 0;
        }
        
        return lstInfoUsuariosPorPerfil;
    }
}
