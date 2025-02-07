import React from "react";
import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";

const Footer = () => (
  <AppBar position="fixed" style={{ top: "auto", bottom: 0 }}>
    <Toolbar>
      <Typography variant="body1" align="center" style={{ flexGrow: 1 }}>
        Footer Content
      </Typography>
    </Toolbar>
  </AppBar>
);

export default Footer;
