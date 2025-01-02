package com.onetuks.csphinxserver.adapter.out.persistence.entity;

import com.onetuks.csphinxserver.domain.collection.CollectionType;
import jakarta.persistence.Id;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "collections")
public class CollectionEntity {

  @Id private String id;

  @Field(name = "collection_name")
  private String collectionName;

  @Field(name = "collection_type")
  private CollectionType collectionType;

  @Field(name = "included_question_ids")
  private Set<String> includedQuestionIds;

  public CollectionEntity(
      String collectionName, CollectionType collectionType, Set<String> includedQuestionIds) {
    this.collectionName = collectionName;
    this.collectionType = collectionType;
    this.includedQuestionIds = includedQuestionIds;
  }
}
