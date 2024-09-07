import React from 'react';
import { Link } from 'react-router-dom';

function HomePage() {
    return (
        <div>
            <h1>Welcome to D&D Social Media</h1>
            <Link to="/signup">Sign Up</Link>
            <br />
            <Link to="/signin">Sign In</Link>
        </div>
    );
}

export default HomePage;

