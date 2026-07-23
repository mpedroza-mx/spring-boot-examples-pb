package org.learning.spring.spring_boot_mongodb;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "outboxevent")
public class OutboxEvent {

    @JsonProperty("_id")
    @Id
    @EqualsAndHashCode.Include
    private ObjectId _id;

    @Field("aggregateid")
    private ObjectId aggregateId;

    @Field("aggregatetype")
    private String aggregateType;

    private String type;

    private long timestamp;

    private Movie payload;


}
