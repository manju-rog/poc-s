import React from "react";
import { Button } from "@mui/material";

function ClearButton({ onClear }) {
  return (
    <Button variant="contained" color="secondary" onClick={onClear}>
      Clear
    </Button>
  );
}

export default ClearButton;
