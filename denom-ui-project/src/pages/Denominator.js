import React, { useState } from "react";
import { TextField, Button, Typography, Box } from "@mui/material";
import DenominationResult from "../components/DenominationResult";
import ClearButton from "../components/ClearButton";

function calculateDenominations(amount) {
  const denominations = [1000, 500, 100, 20, 10, 5, 2, 1];
  const result = {};
  let remainingAmount = amount;

  for (let denom of denominations) {
    if (remainingAmount >= denom) {
      result[denom] = Math.floor(remainingAmount / denom);
      remainingAmount %= denom;
    }
  }
  return result;
}

const Denominator = () => {
  const [amount, setAmount] = useState("");
  const [result, setResult] = useState({});

  const handleCalculate = () => {
    const numAmount = parseInt(amount, 10);
    if (numAmount > 0) {
      setResult(calculateDenominations(numAmount));
    } else {
      alert("Enter a valid positive number.");
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        gap: 2,
        mt: 4,
      }}
    >
      <Typography variant="h4">Denominator</Typography>

      <TextField
        label="Enter Amount"
        variant="outlined"
        value={amount}
        onChange={(e) => setAmount(e.target.value)}
      />

      <Button variant="contained" color="primary" onClick={handleCalculate}>
        Calculate
      </Button>

      <ClearButton
        onClear={() => {
          setAmount("");
          setResult({});
        }}
      />

      <DenominationResult result={result} />
    </Box>
  );
};

export default Denominator;
