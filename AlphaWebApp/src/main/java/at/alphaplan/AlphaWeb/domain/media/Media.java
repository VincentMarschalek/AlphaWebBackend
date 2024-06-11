package at.alphaplan.AlphaWeb.domain.media;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.time.Instant;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "media")
public class Media {
  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;

  private String filename;
  private String mimeType; // z.B. image/jpeg, video/mp4
  private long size;
  private int width;
  private int height;
  private Instant createdAt;

  public Media(ObjectId id, String filename, String mimeType, long size, int width, int height) {
    this.id = id;
    this.filename = filename;
    this.mimeType = mimeType;
    this.size = size;
    this.width = width;
    this.height = height;
    this.createdAt = Instant.now();
  }
}
