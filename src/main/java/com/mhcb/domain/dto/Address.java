package com.mhcb.domain.dto;

import com.mhcb.domain.Locality;
import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
public class Address {

    private String region;
    private String district;
    @Enumerated(EnumType.STRING)
    private Locality localityType;
    private String localityName;
    private String streetType;
    private String buildingNumber;
    private Long block;

    public String getFullStreetName(){
        return region + " " + district + " " + localityType + " " + localityName + " " + streetType + " " + buildingNumber + " " + block;
    }
}
