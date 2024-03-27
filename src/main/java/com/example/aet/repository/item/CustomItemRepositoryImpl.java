package com.example.aet.repository.item;

import com.example.aet.model.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomItemRepositoryImpl implements CustomItemRepository {
    private final MongoTemplate mongoTemplate;

    @Override
    public List<Item> findLatest(String assetId, String userId) {
        Query query = new Query();
        List<Criteria> criteriaList = List.of(
                Criteria.where("assetId").is(assetId),
                Criteria.where("userId").is(userId)
        );
        query.addCriteria(new Criteria().andOperator(criteriaList));
        query.with(Sort.by(Sort.Direction.DESC, "date"));
        query.limit(5);

        return mongoTemplate.find(query, Item.class);
    }
}
