package ua.skillsup.javacourse.blog.service;

import ua.skillsup.javacourse.blog.domain.Author;
import ua.skillsup.javacourse.blog.domain.Message;

import java.util.List;

public interface BlogService {
    List<Message> getRecentMessages(int count);
    void saveMessage(Message message);
    void saveAuthor(Author author);
    Author getAuthor(long id);
    void startFollowing(Author follower, Author followee);
    List<Message> getMessagesForAuthor(Author author);
    List<Message> getMessagesForAuthor(String username);
    Author getAuthor(String username);
    Message getMessageById(long id);
    void deleteMessage(long id);
    List<Author> getAllAuthors();
}
