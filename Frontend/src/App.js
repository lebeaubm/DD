import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CharacterPage from './pages/CharacterPage';
import HomePage from './pages/HomePage';
import SignUpPage from './pages/SignUpPage';
import SignInPage from './pages/SignInPage';
import CharacterDetail from "./pages/CharacterDetail";
import BattleSimulator from './components/BattleSimulator';
import TeamSelectionPage from './pages/TeamSelectionPage';
import BattlePage from './pages/BattlePage';
import BattleLogPage from './pages/BattleLogPage'; 

function App() {
    
    const handleTeamSubmit = (team1, team2) => {
        // Send team data to backend for battle simulation
        fetch('http://localhost:8081/api/battle/team', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ team1, team2 })
        })
        .then(response => response.json())
        .then(data => {
            // Redirect to battle page with battle log or show it here
            console.log('Battle result:', data);
        })
        .catch(error => {
            console.error('Error starting the battle:', error);
        });
    };
    
    
    
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/signup" element={<SignUpPage />} />
                <Route path="/signin" element={<SignInPage />} />
                {/* <Route path="/characters" element={<CharacterPage />} /> */}
                <Route path="/characters" element={<CharacterPage />} />
                <Route path="/characters/:id" element={<CharacterDetail />} /> {/* New route for character sheet */}
                <Route path="/battle-simulator" element={<BattleSimulator />} />
                <Route path="/team-selection" element={<TeamSelectionPage onTeamSubmit={handleTeamSubmit} />} />
                <Route path="/battle" element={<BattlePage />} /> {/* Battle log page */}
                <Route path="/battle-log" element={<BattleLogPage />} />
            </Routes>
        </Router>
    );
}

export default App;
