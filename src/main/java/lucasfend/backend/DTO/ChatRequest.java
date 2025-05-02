package lucasfend.backend.DTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatRequest {
    private String model = "gpt-4o-mini";
    private List<Message> messages = new ArrayList<>();
}
