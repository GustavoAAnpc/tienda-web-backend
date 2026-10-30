package com.utp.tienda.modelos.servicios;

import com.utp.tienda.modelos.entidades.*;
import com.utp.tienda.modelos.repositorios.*;
import com.utp.tienda.vistas.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PerfilService {
    private final UsuarioRepository usuarios;
    private final DatosPersonalesRepository datosRepo;
    private final CorreoElectronicoRepository correos;
    private final TelefonoRepository telefonos;
    private final DireccionRepository direcciones;
    private final DistritoRepository distritos;

    public PerfilService(UsuarioRepository usuarios, DatosPersonalesRepository datosRepo,
                         CorreoElectronicoRepository correos, TelefonoRepository telefonos,
                         DireccionRepository direcciones, DistritoRepository distritos) {
        this.usuarios = usuarios;
        this.datosRepo = datosRepo;
        this.correos = correos;
        this.telefonos = telefonos;
        this.direcciones = direcciones;
        this.distritos = distritos;
    }

    @Transactional(readOnly = true)
    public PerfilDTO consultar(Integer idUsuario) {
        Usuario usuario = usuarios.findById(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        DatosPersonales datos = datosRepo.findByUsuario_IdUsuario(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("El usuario no tiene datos personales"));
        Integer id = datos.getIdDatosPersonales();
        List<CorreoElectronico> listaCorreos = correos.findByDatosPersonales_IdDatosPersonales(id);
        List<Telefono> listaTelefonos = telefonos.findByDatosPersonales_IdDatosPersonales(id);
        List<Direccion> listaDirecciones = direcciones.findByDatosPersonales_IdDatosPersonales(id);
        String correo = listaCorreos.isEmpty() ? "" : listaCorreos.get(0).getCorreoElectronico();
        String telefono = listaTelefonos.isEmpty() ? "" : listaTelefonos.get(0).getNumeroTelefono();
        Direccion dir = listaDirecciones.isEmpty() ? null : listaDirecciones.get(0);
        return new PerfilDTO(idUsuario, datos.getNombres(), datos.getApellidos(), datos.getDni(),
            correo, telefono, dir == null ? "" : dir.getCalleAvenida(),
            dir == null || dir.getDistrito() == null ? "" : dir.getDistrito().getNombreDistrito(),
            usuario.getRol() == null ? "" : usuario.getRol().getNombreRol());
    }

    @Transactional
    public PerfilDTO actualizar(Integer idUsuario, ActualizarPerfilDTO request) {
        DatosPersonales datos = datosRepo.findByUsuario_IdUsuario(idUsuario)
            .orElseThrow(() -> new IllegalArgumentException("El usuario no tiene datos personales"));
        String documento = request.documento().trim();
        datosRepo.findByDni(documento).ifPresent(otro -> {
            if (!otro.getIdDatosPersonales().equals(datos.getIdDatosPersonales()))
                throw new IllegalArgumentException("El documento pertenece a otro usuario");
        });
        datos.setNombres(request.nombres().trim());
        datos.setApellidos(request.apellidos().trim());
        datos.setDni(documento);
        datosRepo.save(datos);

        Integer id = datos.getIdDatosPersonales();
        if (request.telefono() != null) {
            List<Telefono> lista = telefonos.findByDatosPersonales_IdDatosPersonales(id);
            String numero = request.telefono().trim();
            if (numero.isEmpty()) {
                if (!lista.isEmpty()) telefonos.delete(lista.get(0));
            } else {
                Telefono telefono = lista.isEmpty() ? new Telefono() : lista.get(0);
                telefono.setDatosPersonales(datos);
                telefono.setNumeroTelefono(numero);
                if (lista.isEmpty()) telefono.setTipoTelefono("MOVIL");
                telefonos.save(telefono);
            }
        }
        if (request.direccion() != null) {
            List<Direccion> lista = direcciones.findByDatosPersonales_IdDatosPersonales(id);
            String calle = request.direccion().trim();
            if (calle.isEmpty()) {
                if (!lista.isEmpty()) direcciones.delete(lista.get(0));
            } else {
                Direccion direccion = lista.isEmpty() ? new Direccion() : lista.get(0);
                direccion.setDatosPersonales(datos);
                direccion.setCalleAvenida(calle);
                if (lista.isEmpty()) {
                    direccion.setTipoDireccion("DOMICILIO");
                    direccion.setNumero("S/N");
                }
                if (request.idDistrito() != null) {
                    direccion.setDistrito(distritos.findById(request.idDistrito())
                        .orElseThrow(() -> new IllegalArgumentException("Distrito no encontrado")));
                }
                if (direccion.getDistrito() == null)
                    throw new IllegalArgumentException("Indique idDistrito para registrar una nueva dirección");
                direcciones.save(direccion);
            }
        }
        return consultar(idUsuario);
    }
}
