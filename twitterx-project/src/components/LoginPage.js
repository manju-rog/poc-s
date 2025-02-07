import React from "react";
import "./../styles/LoginPage.css";

const LoginPage = ({ onLogin }) => {
  const handleLogin = () => {
    // Simulate a successful login
    const mockUser = {
      name: "John Doe",
      username: "@johndoe",
      profilePic: "https://via.placeholder.com/50",
    };
    onLogin(mockUser); // Pass the mock user data to the parent component
  };

  return (
    <div className="login-container">
      <div className="logo">X</div>
      <div className="login-content">
        <h1>Happening now</h1>
        <h2>Join today.</h2>
        <button className="login-btn google" onClick={handleLogin}>
          Sign in with Google
        </button>
        <button className="login-btn apple">Sign up with Apple</button>
        <p>or</p>
        <button className="create-account-btn">Create account</button>
        <p className="login-footer">
          Already have an account? <a href="#">Sign in</a>
        </p>
      </div>
    </div>
  );
};

export default LoginPage;
