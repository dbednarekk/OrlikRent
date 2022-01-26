package com.pas.orlikrent.dto.pitch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PitchRentDTO2 {

    private String accountID;
    private String pitchID;
    private String start_date_rental;
    private String end_date_rental;
    private Boolean active;


}
