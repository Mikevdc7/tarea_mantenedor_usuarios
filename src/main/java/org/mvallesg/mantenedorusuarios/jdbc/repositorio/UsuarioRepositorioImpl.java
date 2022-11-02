package org.mvallesg.mantenedorusuarios.jdbc.repositorio;

import org.mvallesg.mantenedorusuarios.jdbc.modelo.Usuario;
import org.mvallesg.mantenedorusuarios.jdbc.util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositorioImpl implements Repositorio<Usuario>{

    // Recuperamos el conector a la Base de datos
    private Connection getConnection() throws SQLException {
        return ConexionBD.getInstance();
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM usuarios ";

        try(Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()){
                Usuario usuario = crearUsuario(rs);
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }


    @Override
    public Usuario consultar(Long id) {
        Usuario usuario = null;
        String sql = "SELECT * " +
                     "FROM usuarios " +
                     "WHERE id=?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setLong(1, id);
            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
                    usuario = crearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    @Override
    public void insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (USERNAME, PASSWORD, EMAIL) " +
                     "VALUES (?, ?, ?)";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Usuario usuario){
        String sql = "UPDATE usuarios " +
                     "SET username=?, password=?, email=? " +
                     "WHERE id=?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getEmail());
            stmt.setLong(4, usuario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        String sql = "DELETE FROM usuarios " +
                     "WHERE id=?";
        try(PreparedStatement stmt = getConnection().prepareStatement(sql)){
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Usuario crearUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setUsername(rs.getString("username"));
        usuario.setPassword(rs.getString("password"));
        usuario.setEmail(rs.getString("email"));
        return usuario;
    }
}