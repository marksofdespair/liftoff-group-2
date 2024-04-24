package org.teamlaika.laikaspetpark.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ZipApi(@JsonProperty("zip_code") Integer zipcode, Float distance) implements Comparable<ZipApi> {
    public int compareTo(ZipApi zipApi) {
        return distance().compareTo(zipApi.distance());
    }
}
