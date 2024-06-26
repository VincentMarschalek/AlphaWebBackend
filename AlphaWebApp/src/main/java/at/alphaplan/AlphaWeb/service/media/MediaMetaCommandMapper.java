package at.alphaplan.AlphaWeb.service.media;

import at.alphaplan.AlphaWeb.domain.media.Media;
import at.alphaplan.AlphaWeb.presentation.commands.Commands.MediaMetaCommand;
import org.bson.types.ObjectId;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// @Mapper
public interface MediaMetaCommandMapper {
  MediaMetaCommandMapper INSTANCE = Mappers.getMapper(MediaMetaCommandMapper.class);

  @Mapping(target = "id", source = "id")
  @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
  Media toMedia(MediaMetaCommand mediaMeta, ObjectId id);
}
