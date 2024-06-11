package at.alphaplan.AlphaWeb.service.media;

import at.alphaplan.AlphaWeb.domain.media.Media;
import at.alphaplan.AlphaWeb.presentation.commands.Commands;
import javax.annotation.processing.Generated;
import org.bson.types.ObjectId;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-11T19:53:32+0200",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 21.0.1 (Oracle Corporation)"
)
public class MediaMetaCommandMapperImpl implements MediaMetaCommandMapper {

    @Override
    public Media toMedia(Commands.MediaMetaCommand mediaMeta, ObjectId id) {
        if ( mediaMeta == null && id == null ) {
            return null;
        }

        Media media = new Media();

        if ( mediaMeta != null ) {
            media.setFilename( mediaMeta.filename() );
            media.setMimeType( mediaMeta.mimeType() );
            media.setSize( mediaMeta.size() );
            media.setWidth( mediaMeta.width() );
            media.setHeight( mediaMeta.height() );
        }
        media.setId( id );
        media.setCreatedAt( java.time.Instant.now() );

        return media;
    }
}
