package pl.sda.springbootdata.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAllByTitle(String title);

    @Query("select t from Todo t where t.title = :title")
    List<Todo> findTodosByTitleQuery(@Param("title") String title);
}
