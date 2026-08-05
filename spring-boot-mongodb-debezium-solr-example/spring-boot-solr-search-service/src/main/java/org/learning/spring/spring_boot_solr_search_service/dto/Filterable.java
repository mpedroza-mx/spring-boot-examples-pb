package org.learning.spring.spring_boot_solr_search_service.dto;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
include = JsonTypeInfo.As.PROPERTY,
property = "operator", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EqualsFilter.class,name = "EQUALS"),
        @JsonSubTypes.Type(value = RangeFilter.class,name = "RANGE"),
        @JsonSubTypes.Type(value = InFilter.class,name = "IN")
})
public sealed interface Filterable permits EqualsFilter, RangeFilter, InFilter {
    String getField();
    String getOperator();

}
