import React from "react";
import Sidebar from "./Sidebar";
import Feed from "./Feed";
import Rightbar from "./Rightbar";
import "./../styles/HomeFeed.css";

const HomeFeed = ({ user, onLogout }) => {
    return (
      <div className="homefeed-container">
        <Sidebar user={user} />
        <Feed user={user} />
        <Rightbar />
        <button className="logout-btn" onClick={onLogout}>
          Logout
        </button>
      </div>
    );
  };
  
  export default HomeFeed;
  