package com.pas.orlikrent.dto.pitch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PitchRentDTO2 {
    @NotEmpty
    private String accountID;
    @NotEmpty
    private String pitchID;
    @NotEmpty
    private String start_date_rental;
    @NotEmpty
    private String end_date_rental;
    private Boolean active;


}
