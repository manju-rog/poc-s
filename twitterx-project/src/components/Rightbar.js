import React from "react";
import "./../styles/Rightbar.css";

const Rightbar = () => {
    const trendingTopics = [
      {
        id: 1,
        title: "#ReactJS",
        description: "Trending in Technology",
        tweets: "120K Tweets",
      },
      {
        id: 2,
        title: "#OpenAI",
        description: "Trending in AI",
        tweets: "89K Tweets",
      },
      {
        id: 3,
        title: "#JavaScript",
        description: "Trending in Development",
        tweets: "70K Tweets",
      },
    ];
  
    return (
      <div className="rightbar">
        <h2>What’s happening</h2>
        <ul className="trending-list">
          {trendingTopics.map((topic) => (
            <li className="trending-item" key={topic.id}>
              <h3>{topic.title}</h3>
              <p className="description">{topic.description}</p>
              <p className="tweets">{topic.tweets}</p>
            </li>
          ))}
        </ul>
  
        {/* Footer Section */}
        <footer className="rightbar-footer">
          <p>
            <a href="#">Terms of Service</a> · <a href="#">Privacy Policy</a> ·{" "}
            <a href="#">Help Center</a>
          </p>
        </footer>
      </div>
    );
  };
  

export default Rightbar;
