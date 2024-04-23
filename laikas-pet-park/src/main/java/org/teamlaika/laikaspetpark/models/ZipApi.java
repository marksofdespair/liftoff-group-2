package org.teamlaika.laikaspetpark.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ZipApi(Integer zip_code, Float distance) implements Comparable<ZipApi> {
    public int compareTo(ZipApi zipApi) {
        return distance().compareTo(zipApi.distance());
    }
}
