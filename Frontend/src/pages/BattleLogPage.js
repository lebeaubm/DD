import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const BattlePage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { battleLog } = location.state || {};

    // Check if battleLog is available
    if (!battleLog) {
        console.error('No battle log found. Redirecting to team selection.');
        navigate('/team-selection');
        return null; // Prevent rendering until redirected
    }

    // Check if battleLog is an object and has the expected properties
    if (typeof battleLog !== 'object' || !battleLog.logs || !Array.isArray(battleLog.logs)) {
        console.error('Invalid battle log format.');
        return <div>Error: Invalid battle log format.</div>;
    }

    return (
        <div>
            <h1>Battle Log</h1>
            {/* Render the result and summary */}
            <h2>Result: {battleLog.result || 'No result available'}</h2>
            <h3>Summary: {battleLog.summary || 'No summary available'}</h3>

            {/* Render logs array */}
            <ul>
                {battleLog.logs.map((log, index) => (
                    <li key={index}>{log}</li>
                ))}
            </ul>
            <button onClick={() => navigate('/team-selection')}>Back to Team Selection</button>
        </div>
    );
};

export default BattlePage;

