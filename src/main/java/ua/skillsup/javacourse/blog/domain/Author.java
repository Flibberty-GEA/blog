package ua.skillsup.javacourse.blog.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

//import javax.validation.constraints.Size;
//import javax.validation.constraints.Pattern;
//import java.time.LocalDate;
//import java.util.List;
//import static java.util.Arrays.asList;

//import org.codehaus.jackson.annotate.JsonIgnore;
//import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString(exclude = {"id", "password"}, includeFieldNames = true)

@Entity
@Table(name="authors")
//@XmlRootElement
public class Author implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(name="username", unique=true)
    /*@Size(min=3, max=20, message=
                    "Username must be between 3 and 20 characters long.")
    @Pattern(regexp="^[a-zA-Z0-9]+$",
            message="Username must be alphanumeric with no spaces")*/
    private String username;

    @Column(name="password")
    /*@Size(min=6, max=20,
            message="The password must be at least 6 characters long.")*/
    private String password;

    @Column(name="fullname")
    /*@Size(min=3, max=50, message=
            "Your full name must be between 3 and 50 characters long.")*/
    private String fullName;

    @Column(name="email")
    /*@Pattern(regexp="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            message="Invalid email address.")*/
    private String email;

    @Column(name="update_by_email")
    private boolean updateByEmail;

/*    @Transient
    @JsonIgnore
    public List<Message> getRecentMessages() {
        Message message = new Message();
        message.setId(999L);
        message.setAuthor(this);
        message.setText("TEST SPITTLE #99");
        message.setWhen(LocalDate.now());
        return asList(message);
    }*/

}
