import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CharacterPage from './pages/CharacterPage';
import HomePage from './pages/HomePage';
import SignUpPage from './pages/SignUpPage';
import SignInPage from './pages/SignInPage';

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/signup" element={<SignUpPage />} />
                <Route path="/signin" element={<SignInPage />} />
                {/* <Route path="/characters" element={<CharacterPage />} /> */}
                <Route path="/characters" element={<CharacterPage />} />
            </Routes>
        </Router>
    );
}

export default App;
