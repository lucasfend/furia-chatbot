package lucasfend.backend.DTO;

import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "collection")
public class Content {
    @Id
    private String id;
    private String title;
    private String content;

}
