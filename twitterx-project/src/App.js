import React, { useState } from "react";
import LoginPage from "./components/LoginPage";
import HomeFeed from "./components/HomeFeed";

function App() {
  const [user, setUser] = useState(null); // State to manage logged-in user

  const handleLogin = (userData) => {
    setUser(userData); // Update the user state when logged in
  };

  const handleLogout = () => {
    setUser(null); // Clear the user state when logged out
  };

  return (
    <div className="App">
      {user ? (
        <HomeFeed user={user} onLogout={handleLogout} />
      ) : (
        <LoginPage onLogin={handleLogin} />
      )}
    </div>
  );
}

export default App;
