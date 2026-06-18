package com.onetuks.csphinxserver.adapter.out.persistence.repository;

import com.onetuks.csphinxserver.adapter.out.persistence.entity.QWorkbookEntity;
import com.onetuks.csphinxserver.adapter.out.persistence.entity.WorkbookEntity;
import com.onetuks.csphinxserver.domain.workbook.CollectionType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class WorkbookEntityQueryDslRepository {

  private final JPAQueryFactory jpaQueryFactory;

  public WorkbookEntityQueryDslRepository(JPAQueryFactory jpaQueryFactory) {
    this.jpaQueryFactory = jpaQueryFactory;
  }

  public Page<WorkbookEntity> findAll(
      String keyword, CollectionType collectionType, Pageable pageable) {
    QWorkbookEntity workbookEntity = QWorkbookEntity.workbookEntity;

    List<WorkbookEntity> content =
        jpaQueryFactory
            .selectFrom(workbookEntity)
            .where(titleContains(keyword), collectionTypeEquals(collectionType))
            .orderBy(orderSpecifier(pageable.getSort(), workbookEntity))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

    long count =
        Objects.requireNonNull(
            jpaQueryFactory
                .select(workbookEntity.count())
                .from(workbookEntity)
                .where(titleContains(keyword), collectionTypeEquals(collectionType))
                .fetchOne());

    return new PageImpl<>(content, pageable, count);
  }

  private BooleanExpression titleContains(String keyword) {
    return Objects.isNull(keyword)
        ? null
        : QWorkbookEntity.workbookEntity.title.containsIgnoreCase(keyword);
  }

  private BooleanExpression collectionTypeEquals(CollectionType collectionType) {
    return Objects.isNull(collectionType)
        ? null
        : QWorkbookEntity.workbookEntity.collectionType.eq(collectionType);
  }

  private OrderSpecifier<?>[] orderSpecifier(Sort sort, QWorkbookEntity workbookEntity) {
    return sort.stream()
        .map(
            order -> {
              Order direction = order.isAscending() ? Order.ASC : Order.DESC;

              if (Objects.equals("title", order.getProperty())) {
                return new OrderSpecifier<>(direction, workbookEntity.title);
              } else if (Objects.equals("collectionType", order.getProperty())) {
                return new OrderSpecifier<>(direction, workbookEntity.collectionType);
              }

              return null;
            })
        .filter(Objects::nonNull)
        .toArray(OrderSpecifier[]::new);
  }
}
