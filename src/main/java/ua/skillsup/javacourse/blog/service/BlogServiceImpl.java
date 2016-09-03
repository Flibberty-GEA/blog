    package ua.skillsup.javacourse.blog.service;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Propagation;
    import org.springframework.transaction.annotation.Transactional;
    import ua.skillsup.javacourse.blog.domain.Author;
    import ua.skillsup.javacourse.blog.domain.Message;
    import ua.skillsup.javacourse.blog.persistence.BlogDAO;

    import java.util.List;

    import static java.lang.Math.min;
    import static java.util.Collections.reverse;

    @Service("blogService")
    @Transactional(propagation= Propagation.REQUIRED)
    public class BlogServiceImpl implements BlogService {

        @Autowired
        BlogDAO blogDao;

        @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
        public List<Message> getRecentMessages(int count) {
            List<Message> recentMessages =
                    blogDao.getRecentMessage();

            reverse(recentMessages);

            return recentMessages.subList(0,
                    min(49, recentMessages.size()));
        }

        public void saveMessage(Message message) {
            message.setWhen(java.time.LocalDate.now());
            blogDao.saveMessage(message);
        }

        public void saveAuthor(Author author) {
            if(author.getId() == null) {
                blogDao.addAuthor(author);
            } else {
                blogDao.saveAuthor(author);
            }
        }

        @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
        public Author getAuthor(long id) {
            return blogDao.getAuthorById(id);
        }

        public void startFollowing(Author follower, Author followee) {
            // TODO Auto-generated method stub
        }

        public List<Message> getMessagesForAuthor(Author author) {
            return blogDao.getMessagesForAuthor(author);
        }

        public List<Message> getMessagesForAuthor(String username) {
            Author author = blogDao.getAuthorByUsername(username);
            return getMessagesForAuthor(author);
        }

        public Author getAuthor(String username) {
            return blogDao.getAuthorByUsername(username);
        }

        public Message getMessageById(long id) {
            return blogDao.getMessageById(id);
        }

        public void deleteMessage(long id) {
            blogDao.deleteMessage(id);
        }

        public List<Author> getAllAuthors() {
            return blogDao.findAllAuthors();
        }
    }
