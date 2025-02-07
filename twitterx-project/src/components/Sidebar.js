import React from "react";
import "./../styles/Sidebar.css";
import { FaHome, FaHashtag, FaBell, FaEnvelope, FaUser, FaEllipsisH } from "react-icons/fa";

const Sidebar = ({ user }) => {
  return (
    <div className="sidebar">
      <ul>
        <li>
          <FaHome className="icon" /> Home
        </li>
        <li>
          <FaHashtag className="icon" /> Explore
        </li>
        <li>
          <FaBell className="icon" /> Notifications
        </li>
        <li>
          <FaEnvelope className="icon" /> Messages
        </li>
        <li>
          <FaUser className="icon" /> Profile
        </li>
        <li>
          <FaEllipsisH className="icon" /> More
        </li>
      </ul>

      {/* Display logged-in user's profile */}
      {user && (
        <div className="user-profile">
          <img src={user.profilePic} alt="Profile" className="profile-pic" />
          <div>
            <h3>{user.name}</h3>
            <p className="username">{user.username}</p>
          </div>
        </div>
      )}
    </div>
  );
};

export default Sidebar;
