package ua.skillsup.javacourse.blog.domain;

import lombok.Data;
import lombok.ToString;
//import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

import static javax.persistence.GenerationType.AUTO;

@Data
@ToString(exclude = {"id"}, includeFieldNames = true)

@Entity
@Table(name="messages")
public class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id")
    private Author author;

    @Column(name="messageText")
    /*@NotNull
    @Size(min=1, max=140)*/
    private String text;

    @Column(name="postedTime")
    /*@DateTimeFormat(pattern="M/d/yy h:mm")*/
    private LocalDate when;

    public Message() {
        this.author = new Author();
        this.author.setId((long)1);
    }

}
