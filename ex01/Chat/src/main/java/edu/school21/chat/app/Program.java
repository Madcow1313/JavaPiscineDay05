package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessageRepositoryJdbcImpl;

import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        JdbcDataSource dataSource = new JdbcDataSource();

        MessageRepositoryJdbcImpl repositoryJdbc =
                new MessageRepositoryJdbcImpl(dataSource.getHikariDataSource());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter message id");
        Long id = scanner.nextLong();
        if (repositoryJdbc.findById(id).isPresent()){
            Message message = repositoryJdbc.findById(id).get();
            System.out.println("Message: {");
            System.out.print("id=" + id + ",");
            printAuthor(message);
            printRoom(message);
            System.out.println("text=" + message.getText() + ",");
            System.out.println("dateTime=" + message.getDateTime());
            System.out.println("}");
        }
        else{
            System.out.println("No message with such id");
        }
    }

    public static void printAuthor(Message message) {
        System.out.println("author={" + "id=" + message.getId() + ","
            + "login=" + message.getAuthor().getLogin() + ","
            + "password=" + message.getAuthor().getPassword() + ","
            + "createdRooms=" + message.getAuthor().getCreatedRooms() + ","
            +   "rooms=" + message.getAuthor().getInRooms() + "},");
    }

    public static void printRoom(Message message){
        Chatroom room = message.getRoom();
        System.out.println("room={" + "id=" + room.getId() + ","
                + "creator=" + room.getOwner() + ","
                + "messages=" + room.getMessages() + "},");
    }
}
