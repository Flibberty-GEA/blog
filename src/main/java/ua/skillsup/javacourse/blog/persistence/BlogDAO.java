package ua.skillsup.javacourse.blog.persistence;

import ua.skillsup.javacourse.blog.domain.Author;
import ua.skillsup.javacourse.blog.domain.Message;

import java.util.List;

public interface BlogDAO {

    void addAuthor(Author author);

    void saveAuthor(Author author);

    Author getAuthorById(long id);

    List<Message> getRecentMessage();

    void saveMessage(Message message);

    List<Message> getMessagesForAuthor(Author author);

    Author getAuthorByUsername(String username);

    void deleteMessage(long id);

    Message getMessageById(long id);

    List<Author> findAllAuthors();
}
