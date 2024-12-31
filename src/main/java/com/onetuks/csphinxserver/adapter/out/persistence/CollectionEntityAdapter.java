package com.onetuks.csphinxserver.adapter.out.persistence;

import com.onetuks.csphinxserver.adapter.out.persistence.converter.CollectionConverter;
import com.onetuks.csphinxserver.adapter.out.persistence.repository.CollectionEntityMongoRepository;
import com.onetuks.csphinxserver.application.port.out.CollectionPort;
import com.onetuks.csphinxserver.domain.question.Collection;
import com.onetuks.csphinxserver.global.exception.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public class CollectionEntityAdapter implements CollectionPort {

  private final CollectionEntityMongoRepository collectionRepository;
  private final CollectionConverter collectionConverter;

  public CollectionEntityAdapter(
      CollectionEntityMongoRepository collectionRepository,
      CollectionConverter collectionConverter) {
    this.collectionRepository = collectionRepository;
    this.collectionConverter = collectionConverter;
  }

  @Override
  public Collection create(Collection collection) {
    return collectionConverter.toDomain(
        collectionRepository.save(collectionConverter.toEntity(collection)));
  }

  @Override
  public Collection read(String collectionId) {
    return collectionConverter.toDomain(
        collectionRepository.findById(collectionId).orElseThrow(NoSuchEntityException::new));
  }

  @Override
  public Page<Collection> readAll(Pageable pageable) {
    return collectionRepository.findAll(pageable).map(collectionConverter::toDomain);
  }

  @Override
  public void update(Collection collection) {
    collectionRepository.save(collectionConverter.toEntity(collection));
  }

  @Override
  public void delete(String collectionId) {
    collectionRepository.deleteById(collectionId);
  }
}
