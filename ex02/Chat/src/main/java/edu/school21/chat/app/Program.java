package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.JdbcDataSource;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args){
        JdbcDataSource dataSource = new JdbcDataSource();

        MessagesRepositoryJdbcImpl repositoryJdbc =
                new MessagesRepositoryJdbcImpl(dataSource.getHikariDataSource());
        Long messageId = 1L;
        Optional<Message> message = repositoryJdbc.findById(messageId);

        try{
            if (message.isPresent()){
                message.get().setText("Test text");
                repositoryJdbc.save(message.get());
                Long newMessageId = message.get().getId();

                System.out.println(repositoryJdbc.findById(newMessageId).toString());
            }
        }
        catch (RuntimeException e){}
    }
}
