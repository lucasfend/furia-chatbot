package lucasfend.backend.repository;

import lucasfend.backend.DTO.Content;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends MongoRepository<Content, String> {
    List<Content> findByTitleContainingIgnoreCase(String title);
}
