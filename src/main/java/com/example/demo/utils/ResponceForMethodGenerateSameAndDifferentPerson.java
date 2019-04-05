package com.example.demo.utils;

import com.example.demo.domain.StarWarsPerson;
import com.example.demo.domain.StarWarsSpecies;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponceForMethodGenerateSameAndDifferentPerson {

    public StarWarsPerson person;
    public StarWarsPerson person2;
    public StarWarsSpecies species;
    public StarWarsSpecies species2;


    public ResponceForMethodGenerateSameAndDifferentPerson(StarWarsPerson person, StarWarsPerson person2, StarWarsSpecies species2) {
        this.person = person;
        this.person2 = person2;
        this.species2 = species2;
    }

    public ResponceForMethodGenerateSameAndDifferentPerson( StarWarsSpecies species, StarWarsSpecies species2,StarWarsPerson person2) {

        this.species = species;
        this.species2 = species2;
        this.person2 = person2;
    }

    public String people_Species(ResponceForMethodGenerateSameAndDifferentPerson message) {
        String o= "The "+ message.getPerson().getName() + " is human , as " + message.getPerson2().getName() +
                " and not "+ message.getSpecies2().getClassification()+ " as, "+ message.getSpecies2().getName();
        return o;
    }

    public String species_People(ResponceForMethodGenerateSameAndDifferentPerson message) {
        String o = "The "+ message.getSpecies().getName() + " is "+ message.getSpecies().getClassification()+
                ", as " + message.getSpecies2().getName() + " and not human as, "+ message.getPerson2().getName();
        return o;
    }

    @Override
    public String toString() {
        return "Message{" +
                "person=" + person +
                ", person2=" + person2 +
                ", species=" + species +
                ", species2=" + species2 +
                '}';
    }


}
