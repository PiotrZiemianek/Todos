package pl.sda.springbootdata.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;

    @ManyToOne
    private AppUser author;

    public void setAuthor(@NotNull AppUser author) {
        if (this.author == author && this.author != null) {
            this.author.removeTodo(this);
        }
        this.author = author;
        author.getTodos().add(this);
    }

    public Todo(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

