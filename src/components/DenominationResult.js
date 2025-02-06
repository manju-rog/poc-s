import React from "react";
import { Typography, Box } from "@mui/material";

function DenominationResult({ result }) {
  return (
    <Box sx={{ textAlign: "center", mt: 2 }}>
      {Object.keys(result).length > 0 ? (
        <Box>
          <Typography variant="h5">Denominations:</Typography>
          {Object.entries(result).map(([denom, count]) => (
            <Typography key={denom} variant="body1">
              ₹{denom} x {count}
            </Typography>
          ))}
        </Box>
      ) : (
        <Typography variant="h6">No Result</Typography>
      )}
    </Box>
  );
}

export default DenominationResult;
