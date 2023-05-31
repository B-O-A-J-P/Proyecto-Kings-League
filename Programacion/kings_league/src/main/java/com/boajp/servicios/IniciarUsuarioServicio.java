package com.boajp.servicios;

import com.boajp.controladores.Controlador;
import com.boajp.excepciones.ContrasenaNoValidaExcepcion;
import com.boajp.excepciones.UsuarioNoEncontradoExcepcion;
import com.boajp.modelo.CuentaEntidad;
import com.boajp.repositorios.CuentaRepositorio;

public class IniciarUsuarioServicio {
    private CuentaRepositorio cuentaRepositorio;
    private CuentaEntidad usuario;

    public IniciarUsuarioServicio() {
        cuentaRepositorio = new CuentaRepositorio();
        if ( Controlador.getUsuario() != null )
            usuario = Controlador.getUsuario();
    }
    public void encontrarUsuario(String nombreDeUsuario) {
        this.usuario = cuentaRepositorio.buscarCuenta(nombreDeUsuario);
        if (usuario == null)
            throw new UsuarioNoEncontradoExcepcion();
    }

    public boolean iniciarUsuario(char[] contrasenaDeUsuario) throws ContrasenaNoValidaExcepcion {
        String contrasena = new String(contrasenaDeUsuario);
        if (usuario.getContrasena().equals(contrasena)) {
            Controlador.VENTANA.getBarraDeNavegacion().getIniciarSesionBoton().setText("Ajustes");
            Controlador.mostrarPanelDeInicio();
        } else
            throw new ContrasenaNoValidaExcepcion();
        Controlador.setUsuario(usuario);
        return true;
    }

    public CuentaEntidad getUsuario() {
        return usuario;
    }

    public void setUsuario(CuentaEntidad usuario) {
        this.usuario = usuario;
    }
}
