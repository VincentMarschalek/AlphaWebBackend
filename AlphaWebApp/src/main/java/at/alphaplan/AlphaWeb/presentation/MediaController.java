package at.alphaplan.AlphaWeb.presentation;

import static at.alphaplan.AlphaWeb.presentation.commands.Commands.MediaMetaCommand;

import at.alphaplan.AlphaWeb.service.media.MediaService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
  private final MediaService mediaService;

  @PostMapping
  public ResponseEntity<Void> uploadMedia(
      @RequestParam MultipartFile file,
      @RequestParam String filename,
      @RequestParam String mimeType,
      @RequestParam long size,
      @RequestParam int width,
      @RequestParam int height) {
    MediaMetaCommand mediaMeta = new MediaMetaCommand(filename, mimeType, size, width, height);
    mediaService.saveMedia(file, mediaMeta);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/{mediaId}")
  public ResponseEntity<Resource> downloadMedia(@PathVariable ObjectId mediaId) {
    var result = mediaService.retrieveMedia(mediaId);

    Resource resource = result.getFirst();
    String mimeType = result.getSecond();

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(mimeType))
        .cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic().immutable())
        .body(resource);
  }

  @DeleteMapping("/{mediaId}")
  public ResponseEntity<Void> deleteMedia(@PathVariable ObjectId mediaId) {
    mediaService.deleteMedia(mediaId);
    return ResponseEntity.noContent().build();
  }
}
