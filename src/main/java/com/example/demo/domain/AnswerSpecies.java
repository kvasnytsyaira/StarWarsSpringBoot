package com.example.demo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.ArrayList;

@JsonDeserialize
@JsonSerialize
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnswerSpecies implements Serializable {

    @Column
    int count;
    @Column
    String next;
    @Column
    String previous;
    @Column
    @ElementCollection(targetClass = String.class)
    ArrayList<StarWarsSpecies> results;

}
