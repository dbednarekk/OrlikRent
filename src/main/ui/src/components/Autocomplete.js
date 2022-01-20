import React from "react";
import MuiAutocomplete from '@mui/material/Autocomplete';





export default function Autocomplete(
    {
        options,
        inputValue,
        style,
        noOptionsText,
        onChange,
        renderInput,
    }) {

    return (
        <MuiAutocomplete
            options={options}
            inputValue={inputValue}
            style={{width: 300, marginBottom: 16, ...style}}
            noOptionsText={noOptionsText}
            onChange={onChange}
            renderInput={renderInput}
        />
    )
}