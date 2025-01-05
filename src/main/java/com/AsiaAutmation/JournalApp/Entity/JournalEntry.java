package com.AsiaAutmation.JournalApp.Entity;

import com.AsiaAutmation.JournalApp.Enums.Sentiment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "journal_entry")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    private String content;
    private String title;
    private LocalDateTime date;
    private Sentiment sentiment;

}
