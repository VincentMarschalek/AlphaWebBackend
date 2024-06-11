package at.alphaplan.AlphaWeb.service.media;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import at.alphaplan.AlphaWeb.domain.media.Media;
import at.alphaplan.AlphaWeb.presentation.commands.Commands.MediaMetaCommand;
import com.mongodb.client.gridfs.model.GridFSFile;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MediaService {
  private final Logger LOGGER = LoggerFactory.getLogger(MediaService.class);
  private final GridFsTemplate gridFsTemplate;
  private static final MediaMetaCommandMapper mapper = MediaMetaCommandMapper.INSTANCE;

  // Speichere Medien
  public List<Media> saveMedias(MultipartFile[] mediaFiles, MediaMetaCommand[] mediaMetas) {
    if (mediaFiles.length != mediaMetas.length) {
      throw new IllegalArgumentException("Anzahl der Dateien und Metadaten muss übereinstimmen.");
    }

    return IntStream.range(0, mediaFiles.length)
        .mapToObj(i -> saveMedia(mediaFiles[i], mediaMetas[i]))
        .collect(Collectors.toList());
  }

  public Media saveMedia(MultipartFile mediaFile, MediaMetaCommand mediaMeta) {
    try {
      ObjectId mediaId =
          gridFsTemplate.store(
              mediaFile.getInputStream(), mediaMeta.filename(), mediaMeta.mimeType());
      return mapper.toMedia(mediaMeta, mediaId);
    } catch (Exception e) {
      throw new RuntimeException("Speichern des Mediums fehlgeschlagen.", e);
    }
  }

  // Abrufen von Medien
  public Pair<Resource, String> retrieveMedia(ObjectId mediaId) {
    try {
      GridFSFile file = gridFsTemplate.findOne(query(where("_id").is(mediaId)));
      GridFsResource resource = gridFsTemplate.getResource(file);
      return Pair.of(resource, getMimeType(file));
    } catch (Exception e) {
      throw new RuntimeException("Abrufen des Mediums fehlgeschlagen.", e);
    }
  }

  // Löschen von Medien
  public void deleteMedia(ObjectId mediaId) {
    try {
      gridFsTemplate.delete(query(where("_id").is(mediaId)));
    } catch (Exception e) {
      throw new RuntimeException("Löschen des Mediums fehlgeschlagen.", e);
    }
  }

  // Methode zum Löschen einer Liste von Medien
  public void deleteMedias(List<Media> mediaList) {
    List<ObjectId> mediaIds = mediaList.stream().map(Media::getId).collect(Collectors.toList());
    deleteMediasById(mediaIds);
  }

  // Löschen einer Liste von Medien-IDs
  public void deleteMediasById(List<ObjectId> mediaIds) {
    try {
      gridFsTemplate.delete(query(where("_id").in(mediaIds)));
    } catch (Exception e) {
      throw new RuntimeException("Löschen der Medien fehlgeschlagen.", e);
    }
  }

  private String getMimeType(GridFSFile file) {
    return Optional.ofNullable(file.getMetadata())
        .map(metadata -> metadata.get("_contentType"))
        .map(Object::toString)
        .orElse("application/octet-stream");
  }
}
