package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> inRooms;

    public User(Long id, String login, String password){
        this.id = id;
        this.login = login;
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getInRooms() {
        return inRooms;
    }

    public void setInRooms(List<Chatroom> inRooms) {
        this.inRooms = inRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(login, user.login)
                && Objects.equals(password, user.password) && Objects.equals(createdRooms, user.createdRooms)
                && Objects.equals(inRooms, user.inRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, inRooms);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", Password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", inRooms=" + inRooms +
                '}';
    }
}
