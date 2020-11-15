package com.pwr.jestsprawa.model;

import com.pwr.jestsprawa.model.nominatim.NominatimAddress;
import lombok.Data;

@Data
public class LocationDataDto {

    private Boolean isAvailable;

    private NominatimAddress address;

}
