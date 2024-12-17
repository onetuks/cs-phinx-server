package com.onetuks.csphinxserver.adapter.in.web;

import com.onetuks.csphinxserver.adapter.in.dto.Collections;
import com.onetuks.csphinxserver.application.command.question.CollectionAddCommand;
import com.onetuks.csphinxserver.application.port.in.CollectionUseCases;
import com.onetuks.csphinxserver.domain.question.Collection;
import java.net.URI;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/questions/collections")
public class CollectionRestController {

  private final CollectionUseCases collectionUseCases;

  public CollectionRestController(CollectionUseCases collectionUseCases) {
    this.collectionUseCases = collectionUseCases;
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Collection> postNewCollection(@RequestBody CollectionAddCommand command) {
    Collection collection = collectionUseCases.addCollection(command);

    return ResponseEntity.created(URI.create("/questions/collections/" + collection.collectionId()))
        .build();
  }

  @GetMapping(path = "/{collection-id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Collection> getCollection(
      @PathVariable("collection-id") String collectionId) {
    Collection collection = collectionUseCases.searchCollection(collectionId);

    return ResponseEntity.ok(collection);
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Collections> getCollections(@PageableDefault Pageable pageable) {
    Page<Collection> collections = collectionUseCases.searchAllCollections(pageable);

    return ResponseEntity.ok(new Collections(collections));
  }
}
