package at.alphaplan.AlphaWeb.presentation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaStaticController {

  @GetMapping("/{filename}")
  public ResponseEntity<Resource> download(@PathVariable String filename) throws IOException {

    var resource = new ClassPathResource("static/" + filename);

    Path path = Paths.get(resource.getURI());
    String mimeType = Files.probeContentType(path);

    return ResponseEntity.ok()
        .contentType(MediaType.parseMediaType(mimeType))
        .cacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic().immutable())
        .body(resource);
  }
}
