import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import CssBaseline from "@mui/material/CssBaseline";
import Container from "@mui/material/Container";
import Header from "./components/Header";
import Footer from "./components/Footer";
import Sidebar from "./components/Sidebar";
import Calculator from "./pages/Calculator";
import Denominator from "./pages/Denominator";
import About from "./pages/About";

const theme = createTheme({
  palette: {
    primary: {
      main: "#1976d2",
    },
    secondary: {
      main: "#dc004e",
    },
  },
});

function App() {
  const [open, setOpen] = useState(false);

  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <Router>
        <Header toggleDrawer={() => setOpen(!open)} />
        <Sidebar open={open} toggleDrawer={() => setOpen(!open)} />
        <Container style={{ marginTop: "80px", marginBottom: "80px" }}>
          <Routes>
            <Route path="/calculator" element={<Calculator />} />
            <Route path="/denominator" element={<Denominator />} />
            <Route path="/about" element={<About />} />
          </Routes>
        </Container>
        <Footer />
      </Router>
    </ThemeProvider>
  );
}

export default App;
