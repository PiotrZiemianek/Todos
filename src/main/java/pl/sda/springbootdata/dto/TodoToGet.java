package pl.sda.springbootdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor(staticName = "of")
public class TodoToGet {
    private Long id;
    @NotNull
    @Length(min = 2)
    private String title;

    @NotNull
    @Length(min = 2)
    private String content;
    @NotNull
    private String authorFullName;
}
