package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource){
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

    public void save(Message message){
        Long userId;
        Long roomId;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        if (checkMessage(message)){
            try {
                connection = dataSource.getConnection();
                userId = message.getAuthor().getId();
                roomId = message.getRoom().getId();
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM chat.users WHERE id = " + userId);

                if (!resultSet.next()){
                    throw new NotSavedSubEntityException("User with such id doesn't exist");
                }
                resultSet = statement.executeQuery("SELECT * FROM chat.chatrooms WHERE id = " + roomId);
                if (!resultSet.next()){
                    throw new NotSavedSubEntityException("Chatroom with such id does't exist");
                }
                resultSet = statement.executeQuery("INSERT INTO chat.messages (author, room, text) "
                        + "VALUES (" + userId + ", " + roomId + ", '" + message.getText() + "') " + "RETURNING id");
                if (!resultSet.next()){
                    throw new NotSavedSubEntityException("Insertion failed");
                }
                message.setId(resultSet.getLong(1));
            }
            catch (SQLException e){
                System.out.println("Something went wrong");
                System.out.println("Exception caught: " + e);
            }

        }

    }

    boolean checkMessage(Message message){
        if (message.getAuthor() == null || message.getAuthor().getId() == null) {
            return false;
        }
        if (message.getRoom() == null || message.getRoom().getId() == null) {
            return false;
        }
        if (message.getRoom().getOwner() == null || message.getRoom().getOwner().getId() == null) {
            return false;
        }
        if (message.getText() == null) {
            return false;
        }
        return true;
    }
}
