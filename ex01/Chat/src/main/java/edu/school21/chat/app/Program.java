package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        JdbcDataSource dataSource = new JdbcDataSource();

        MessagesRepositoryJdbcImpl repositoryJdbc =
                new MessagesRepositoryJdbcImpl(dataSource.getHikariDataSource());
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter message id");
        Long id = scanner.nextLong();
        if (repositoryJdbc.findById(id).isPresent()){
            Message message = repositoryJdbc.findById(id).get();
            System.out.println(message);
        }
        else{
            System.out.println("No message with such id");
        }
    }
}
