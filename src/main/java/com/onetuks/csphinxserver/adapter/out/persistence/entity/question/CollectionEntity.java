package com.onetuks.csphinxserver.adapter.out.persistence.entity.question;

import com.onetuks.csphinxserver.domain.question.CollectionType;
import jakarta.persistence.Id;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Document(collection = "packages")
public class CollectionEntity {

  @Id private String id;
  private String collectionName;
  private CollectionType collectionType;
  private Set<String> includedQuestionIds;

  public CollectionEntity(
      String collectionName, CollectionType collectionType, Set<String> includedQuestionIds) {
    this.collectionName = collectionName;
    this.collectionType = collectionType;
    this.includedQuestionIds = includedQuestionIds;
  }
}
