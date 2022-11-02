package org.mvallesg.mantenedorusuarios.jdbc.modelo;

public class Usuario {

    private Long id;
    private String username;
    private String password;
    private String email;

    public Usuario() {
    }

    public Usuario(Long id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usarname) {
        this.username = usarname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id +
                " | " +
                username +
                " | " +
                password +
                " | " +
                email;

    }
}
