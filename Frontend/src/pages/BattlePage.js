
import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const BattlePage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { battleLog } = location.state || {}; // Destructure battleLog from location.state

    // Check if battleLog is available
    if (!battleLog) {
        console.error('No battle log found. Redirecting to team selection.');
        navigate('/team-selection');
        return null; // Prevent rendering until redirected
    }

    // Check if battleLog has expected properties
    const { logs = [], result = 'No result available', summary = 'No summary available' } = battleLog;

    // Check the structure of logs
    if (!Array.isArray(logs)) {
        console.error('Invalid battle log format. "logs" should be an array.');
        return <div>Error: Invalid battle log format.</div>;
    }

    return (
        <div>
            <h1>Battle Log</h1>
            
            {/* Render the result and summary */}
            <h2>Result: {result}</h2>
            <h3>Summary: {summary}</h3>

            {/* Render logs array */}
            <ul>
                {logs.map((log, index) => (
                    <li key={index}>{log}</li>
                ))}
            </ul>

            <button onClick={() => navigate('/team-selection')}>Back to Team Selection</button>
        </div>
    );
};

export default BattlePage;




// import React, { useState, useEffect } from 'react';
// import { useLocation } from 'react-router-dom'; // To get battle log passed via state

// const BattlePage = () => {
//     const location = useLocation();
//     const [log, setLog] = useState('');

//     useEffect(() => {
//         // Retrieve battle log from location state (passed after battle)
//         if (location.state && location.state.battleLog) {
//             setLog(location.state.battleLog);
//         } else {
//             setLog('No battle log available');
//         }
//     }, [location.state]);

//     return (
//         <div>
//             <h1>Battle Log</h1>
//             <pre>{log}</pre> {/* Preformatted text for battle log */}
//         </div>
//     );
// };

// export default BattlePage;



















// import React, { useState, useEffect } from 'react';

// function BattlePage() {
//     const [turnOrder, setTurnOrder] = useState([]);
//     const [currentTurn, setCurrentTurn] = useState(null);
//     const [battleResult, setBattleResult] = useState(null);

//     useEffect(() => {
//         // Fetch turn order at the start of the battle
//         fetch('/api/battle/turn-order')
//             .then(response => response.json())
//             .then(data => setTurnOrder(data));

//         // Poll for current turn and result
//         const interval = setInterval(() => {
//             fetch('/api/battle/current-turn')
//                 .then(response => response.json())
//                 .then(data => setCurrentTurn(data));

//             fetch('/api/battle/result')
//                 .then(response => response.json())
//                 .then(data => {
//                     if (data !== "Battle still ongoing") {
//                         setBattleResult(data);
//                         clearInterval(interval);  // Stop polling once battle ends
//                     }
//                 });
//         }, 1000);

//         return () => clearInterval(interval);
//     }, []);

//     return (
//         <div>
//             <h1>Team Battle Simulator</h1>

//             {currentTurn && (
//                 <div>
//                     <h2>It's {currentTurn.character.name}'s turn</h2>
//                     <button onClick={() => fetch('/api/battle/next-turn', { method: 'POST' })}>
//                         End Turn
//                     </button>
//                 </div>
//             )}

//             <h2>Turn Order</h2>
//             <ul>
//                 {turnOrder.map((combatant, index) => (
//                     <li key={index}>{combatant.character.name} (Initiative: {combatant.initiativeRoll})</li>
//                 ))}
//             </ul>

//             {battleResult && (
//                 <div>
//                     <h2>Battle Result: {battleResult}</h2>
//                 </div>
//             )}
//         </div>
//     );
// }

// export default BattlePage;
