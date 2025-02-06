import React from "react";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";
import { Link } from "react-router-dom";

const Sidebar = ({ open, toggleDrawer }) => (
  <Drawer anchor="left" open={open} onClose={toggleDrawer}>
    <List>
      <ListItem button component={Link} to="/calculator" onClick={toggleDrawer}>
        <ListItemText primary="Calculator" />
      </ListItem>
      <ListItem
        button
        component={Link}
        to="/denominator"
        onClick={toggleDrawer}
      >
        <ListItemText primary="Denominator" />
      </ListItem>
      <ListItem button component={Link} to="/about" onClick={toggleDrawer}>
        <ListItemText primary="About" />
      </ListItem>
    </List>
  </Drawer>
);

export default Sidebar;
