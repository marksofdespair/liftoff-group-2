package org.teamlaika.laikaspetpark.models;

import java.util.Comparator;

public record ZipApi(Integer zipCode, Float distance) implements Comparable<ZipApi> {
    public int compareTo(ZipApi zipApi) {
        return distance().compareTo(zipApi.distance());
    }
}
