package ua.skillsup.javacourse.blog.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.skillsup.javacourse.blog.domain.Author;
import ua.skillsup.javacourse.blog.domain.Message;

import java.util.List;

@Repository
public class HibernateBlogDAO implements BlogDAO{
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateBlogDAO(SessionFactory sessionFactory) {//<co id="co_injectSessionFactory"/>
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void addAuthor(Author author) {
            currentSession().save(author);
    }

    @Override
    public void saveAuthor(Author author) {
            currentSession().update(author);
    }

    @Override
    public Author getAuthorById(long id) {
            return (Author) currentSession().get(Author.class, id);
    }

    @Override
    public List<Message> getRecentMessage() {
        // return currentSession().f  loadAll(Message.class); // this isn't right...just a placeholder for now
        return null;
    }

    @Override
    public void saveMessage(Message message) {
            currentSession().save(message);
    }

    @Override
    public List<Message> getMessagesForAuthor(Author author) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Author getAuthorByUsername(String username){
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteMessage(long id) {
            currentSession().delete(getMessageById(id));
    }

    @Override
    public Message getMessageById(long id) {
        return (Message) currentSession().get(Message.class, id);
    }

    @Override
    public List<Author> findAllAuthors() {
        // TODO Auto-generated method stub
        return null;
    }
}