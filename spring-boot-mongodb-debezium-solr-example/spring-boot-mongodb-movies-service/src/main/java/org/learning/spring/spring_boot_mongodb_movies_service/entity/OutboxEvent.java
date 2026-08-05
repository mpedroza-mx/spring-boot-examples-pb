package org.learning.spring.spring_boot_mongodb_movies_service.entity;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@Document(collection = "outboxevent")
public class OutboxEvent {

    @JsonProperty("_id")
    @Id
    @EqualsAndHashCode.Include
    private ObjectId id;

    @Field("aggregateid")
    private ObjectId aggregateId;

    @Field("aggregatetype")
    private String aggregateType;

    private String type;

    private long timestamp;

    private Movie payload;


}
