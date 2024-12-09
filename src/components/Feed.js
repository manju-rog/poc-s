import React, { useState } from "react";
import { motion } from "framer-motion"; // Import framer-motion
import "./../styles/Feed.css";

const Feed = ({ user }) => {
  const [posts, setPosts] = useState([
    {
      id: 1,
      user: "John Doe",
      username: "@johndoe",
      profilePic: "https://via.placeholder.com/50",
      content: "This is my first post on TwitterX! Excited to be here 🚀",
      likes: 5,
      liked: false,
    },
    {
      id: 2,
      user: "Jane Smith",
      username: "@janesmith",
      profilePic: "https://via.placeholder.com/50",
      content: "Frontend development is so much fun! 😍",
      likes: 12,
      liked: false,
    },
  ]);

  const [newPost, setNewPost] = useState("");

  const handlePost = () => {
    if (newPost.trim() === "") return;

    const newPostData = {
      id: posts.length + 1,
      user: user.name,
      username: user.username,
      profilePic: user.profilePic,
      content: newPost,
      likes: 0,
      liked: false,
    };

    setPosts([newPostData, ...posts]);
    setNewPost("");
  };

  const toggleLike = (postId) => {
    setPosts((prevPosts) =>
      prevPosts.map((post) =>
        post.id === postId
          ? { ...post, likes: post.liked ? post.likes - 1 : post.likes + 1, liked: !post.liked }
          : post
      )
    );
  };

  return (
    <div className="feed">
      {/* Welcome Message */}
      <h2>Welcome, {user.name}!</h2>

      {/* Post Box */}
      <div className="post-box">
        <textarea
          placeholder="What’s happening?"
          rows="3"
          value={newPost}
          onChange={(e) => setNewPost(e.target.value)}
        ></textarea>
        <button className="post-btn" onClick={handlePost}>
          Post
        </button>
      </div>

      {/* Display Posts */}
      <div className="posts">
        {posts.map((post) => (
          <motion.div
            className="post"
            key={post.id}
            initial={{ opacity: 0, y: 20 }} // Animation on entry
            animate={{ opacity: 1, y: 0 }}
            transition={{ duration: 0.3 }}
          >
            <img src={post.profilePic} alt="Profile" className="profile-pic" />
            <div className="post-content">
              <h3>{post.user}</h3>
              <p className="username">{post.username}</p>
              <p>{post.content}</p>
              <div className="post-actions">
                <button className="like-btn" onClick={() => toggleLike(post.id)}>
                  {post.liked ? "💙" : "🤍"} {post.likes} Likes
                </button>
              </div>
            </div>
          </motion.div>
        ))}
      </div>
    </div>
  );
};

export default Feed;
