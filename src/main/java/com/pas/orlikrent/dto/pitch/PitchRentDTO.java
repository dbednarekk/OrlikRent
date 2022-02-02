package com.pas.orlikrent.dto.pitch;

import com.pas.orlikrent.dto.accounts.AccountDTO;
import com.pas.orlikrent.model.Pitch;
import com.pas.orlikrent.model.Users.Account;
import com.pas.orlikrent.security.SignableEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PitchRentDTO  {
    @NotEmpty
    private String accountID;
    @NotEmpty
    private String pitchID;
    @NotEmpty
    private LocalDateTime start_date_rental;
    @NotEmpty
    private LocalDateTime end_date_rental;
    private Boolean active;


}
