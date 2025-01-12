package com.onetuks.csphinxserver.adapter.out.persistence.entity;

import com.onetuks.csphinxserver.domain.workbook.CollectionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Entity
@Table(name = "workbooks")
public class WorkbookEntity extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "workbookId", nullable = false)
  private Long workbookId;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description", nullable = false)
  private String description;

  @Enumerated(value = EnumType.STRING)
  @Column(name = "collection_type", nullable = false)
  private CollectionType collectionType;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;
}
