package org.mvallesg.mantenedorusuarios.jdbc;

import org.mvallesg.mantenedorusuarios.jdbc.modelo.*;
import org.mvallesg.mantenedorusuarios.jdbc.repositorio.*;
import org.mvallesg.mantenedorusuarios.jdbc.util.*;

import javax.swing.*;
import java.sql.*;
import java.util.*;

public class ProyectoMantenedorUsuariosJDBC {
    public static void main(String[] args) {

        try(Connection con = ConexionBD.getInstance()){

            Repositorio<Usuario> usuarios = new UsuarioRepositorioImpl();
            int opcionIndice = 0;
            Map<String, Integer> operaciones = new HashMap<>();
            operaciones.put("Actualizar", 1);
            operaciones.put("Eliminar", 2);
            operaciones.put("Agregar", 3);
            operaciones.put("Listar", 4);
            operaciones.put("Salir", 5);

            Object[] listaOperaciones = operaciones.keySet().toArray();
            do{
                Object opcion = JOptionPane.showInputDialog(null,
                                                     "Seleccione una operación",
                                                        "Mantenedor de Usuarios",
                                                             JOptionPane.INFORMATION_MESSAGE,
                                                        null,
                                                             listaOperaciones,
                                                             listaOperaciones[0]);
                if(opcion==null){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una operación");
                } else{
                    opcionIndice = operaciones.get(opcion.toString());
                }
                switch(opcionIndice){
                    case 1:
                        JOptionPane.showMessageDialog(null,"Has seleccionado la opción de actualizar un usuario.");
                        Long id = Long.parseLong((String) JOptionPane.showInputDialog(null,
                                "Indica el id del usuario a modificar: ",
                                "Id a modificar",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null));
                        String username = (String) JOptionPane.showInputDialog(null,
                                "Indica el nombre de usuario: ",
                                "Nombre de usuario",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null);
                        String password = (String) JOptionPane.showInputDialog(null,
                                "Indica el password: ",
                                "Password",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null);
                        String email = (String) JOptionPane.showInputDialog(null,
                                "Indica el email: ",
                                "Email",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null);
                        Usuario usuarioAct = new Usuario(id, username, password, email);
                        usuarios.actualizar(usuarioAct);
                        JOptionPane.showMessageDialog(null,"Usuario actualizado correctamente!");
                        break;

                    case 2:
                        JOptionPane.showMessageDialog(null,"Has seleccionado la opción de eliminar un usuario.");
                        id = Long.parseLong((String) JOptionPane.showInputDialog(null,
                                "Indica el id del usuario a eliminar: ",
                                "Id a eliminar",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null));
                        usuarios.eliminar(id);
                        JOptionPane.showMessageDialog(null,"Usuario eliminado correctamente!");
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null,"Has seleccionado la opción de agregar un usuario.");
                        username = (String) JOptionPane.showInputDialog(null,
                                "Indica el nombre de usuario: ",
                                "Nombre de usuario",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null);
                        password = (String) JOptionPane.showInputDialog(null,
                                "Indica el password: ",
                                "Password",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null);
                        email = (String) JOptionPane.showInputDialog(null,
                                "Indica el email: ",
                                "Email",
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                null,
                                null);
                        Usuario usuarioAdd = new Usuario(0L, username, password, email);
                        usuarios.insertar(usuarioAdd);
                        JOptionPane.showMessageDialog(null,"Usuario añadido correctamente!");
                        break;

                    case 4:
                        JOptionPane.showMessageDialog(null,"Has seleccionado la opción de listar todos los usuarios.");
                        List<Usuario> listaUsuarios = usuarios.listar();
                        System.out.println("ID | USERNAME | PASSWORD | EMAIL");
                        listaUsuarios.stream().forEach(System.out::println);
                        System.out.println();
                        break;
                }
            }while(opcionIndice!=5);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}