package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessageRepositoryJdbcImpl implements MessageRepository{
    private DataSource dataSource;

    public MessageRepositoryJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public Optional<Message> findById(Long id) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM chat.messages WHERE id = " + id);
            if (!resultSet.next())
                return Optional.empty();
            User user = findUserById(resultSet.getLong(2));
            Chatroom chatroom = findChatRoomById(resultSet.getLong(3));
            return (Optional.of(new Message(resultSet.getLong(1), user,
                    chatroom,
                    resultSet.getString(4),
                    resultSet.getTimestamp(5).toLocalDateTime())));

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    private User findUserById(Long id) throws SQLException{
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = dataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM chat.users WHERE id = " + id);
        if (!resultSet.next())
            return null;
        return (new User(resultSet.getLong(1), resultSet.getString(2),
                resultSet.getString(3)));
    }

    private Chatroom findChatRoomById(Long id) throws SQLException{
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        connection = dataSource.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM chat.chatrooms WHERE id = " + id);
        if (!resultSet.next())
            return null;
        return (new Chatroom(resultSet.getLong(1), resultSet.getString(2),
                findUserById(resultSet.getLong(3))));
    }
}
