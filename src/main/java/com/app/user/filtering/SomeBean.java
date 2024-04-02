package com.app.user.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"field1", "field2"}) for static filtering
@JsonFilter("SomeBeanFilter") //for dynamic filtering and name should match to name given in Rest Controller
public class SomeBean {

    private String field1;

    //@JsonIgnore for static filtering
    private String field2;

    //@JsonIgnore for static filtering
    private String field3;


}
