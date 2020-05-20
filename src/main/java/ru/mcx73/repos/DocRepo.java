package ru.mcx73.repos;

import org.springframework.data.repository.CrudRepository;
import ru.mcx73.entity.Docum;

import java.util.List;

public interface DocRepo extends CrudRepository<Docum, String> {
        List<Docum> findByAuthor(String author);
}
