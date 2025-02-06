import React, { useState } from "react";
import { TextField, Button, Typography, Box } from "@mui/material";
import ClearButton from "../components/ClearButton";

const Calculator = () => {
  const [num1, setNum1] = useState("");
  const [num2, setNum2] = useState("");
  const [operation, setOperation] = useState(null);
  const [result, setResult] = useState(null);

  const handleCalculate = () => {
    if (!num1 || !num2 || !operation) {
      alert("Please enter numbers and select an operation.");
      return;
    }

    const n1 = parseFloat(num1);
    const n2 = parseFloat(num2);
    let res = 0;

    switch (operation) {
      case "add":
        res = n1 + n2;
        break;
      case "subtract":
        res = n1 - n2;
        break;
      case "multiply":
        res = n1 * n2;
        break;
      case "divide":
        res = n2 !== 0 ? n1 / n2 : "Cannot divide by zero";
        break;
      default:
        res = "Invalid operation";
    }

    setResult(res);
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
      <Typography variant="h4">Calculator</Typography>

      <TextField
        label="Enter First Number"
        variant="outlined"
        value={num1}
        onChange={(e) => setNum1(e.target.value)}
      />

      <TextField
        label="Enter Second Number"
        variant="outlined"
        value={num2}
        onChange={(e) => setNum2(e.target.value)}
      />

      <Box sx={{ display: "flex", gap: 2 }}>
        <Button
          variant="contained"
          color="primary"
          onClick={() => setOperation("add")}
        >
          +
        </Button>
        <Button
          variant="contained"
          color="primary"
          onClick={() => setOperation("subtract")}
        >
          -
        </Button>
        <Button
          variant="contained"
          color="primary"
          onClick={() => setOperation("multiply")}
        >
          ×
        </Button>
        <Button
          variant="contained"
          color="primary"
          onClick={() => setOperation("divide")}
        >
          ÷
        </Button>
      </Box>

      <Button variant="contained" color="primary" onClick={handleCalculate}>
        Calculate
      </Button>

      <ClearButton
        onClear={() => {
          setNum1("");
          setNum2("");
          setResult(null);
          setOperation(null);
        }}
      />

      <Typography variant="h6">
        {result !== null ? `Result: ${result}` : "No Result"}
      </Typography>
    </Box>
  );
};

export default Calculator;
