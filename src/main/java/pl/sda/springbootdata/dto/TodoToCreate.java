package pl.sda.springbootdata.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


@Data
public class TodoToCreate {

    @NotNull
    @Length(min = 2)
    private String title;

    @NotNull
    @Length(min = 2)
    private String author;

}
