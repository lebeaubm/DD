
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const TeamSelectionPage = () => {
    const [characters, setCharacters] = useState([]); // All characters available for selection
    const [team1, setTeam1] = useState([]); // Characters for Team 1
    const [team2, setTeam2] = useState([]); // Characters for Team 2
    const navigate = useNavigate(); // For navigation to BattlePage

    useEffect(() => {
        // Fetch available characters when the component loads
        fetch('http://localhost:8081/api/characters')
            .then(response => response.json())
            .then(data => setCharacters(data))
            .catch(error => console.error('Failed to fetch characters:', error));
    }, []);

    // Add a character to a team (team1 or team2)
    const addToTeam = (character, team, setTeam) => {
        if (!team.some(c => c.id === character.id)) {
            setTeam([...team, character]);
        }
    };

    // Remove a character from a team
    const removeFromTeam = (character, team, setTeam) => {
        setTeam(team.filter(c => c.id !== character.id));
    };

    const handleSubmit = () => {
        if (team1.length === 0 || team2.length === 0) {
            alert('Both teams must have at least one character!');
            return;
        }

        console.log(" TeamSelection Team 1: ", team1);
        console.log("Team 2: ", team2);


        // Validate all characters have necessary attributes
    const validateTeam = (team) => {
        return team.every(character => 
            character.strength !== undefined &&
            character.dexterity !== undefined &&
            character.hitPoints !== undefined &&
            character.strength !== null &&
            character.dexterity !== null &&
            character.hitPoints !== null
        );
    };

    if (!validateTeam(team1) || !validateTeam(team2)) {
        alert('All characters must have valid attributes like strength, dexterity, and hit points.');
        return;
    }


        

        // Submit the selected teams to the backend for battle simulation
        fetch('http://localhost:8081/api/battle/team', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ team1, team2 })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to start the battle');
            }
            return response.json();
        })
        .then(data => {
            // Navigate to the battle log page with the result
            navigate('/battle', { state: { battleLog: data } });
        })
        .catch(error => {
            console.error('Error starting the battle:', error);
            alert('Error starting the battle');
        });
    };

    return (
        <div>
            <h1>Team Selection</h1>
            <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                {/* Available Characters */}
                <div>
                    <h2>Available Characters</h2>
                    <ul>
                        {characters.map(character => (
                            <li key={character.id}>
                                {character.name} (Class: {character.charClass}, Level: {character.level})
                                <button onClick={() => addToTeam(character, team1, setTeam1)}>Add to Team 1</button>
                                <button onClick={() => addToTeam(character, team2, setTeam2)}>Add to Team 2</button>
                            </li>
                        ))}
                    </ul>
                </div>

                {/* Team 1 */}
                <div>
                    <h2>Team 1</h2>
                    <ul>
                        {team1.map(character => (
                            <li key={character.id}>
                                {character.name} (Class: {character.charClass}, Level: {character.level})
                                <button onClick={() => removeFromTeam(character, team1, setTeam1)}>Remove from Team</button>
                            </li>
                        ))}
                    </ul>
                </div>

                {/* Team 2 */}
                <div>
                    <h2>Team 2</h2>
                    <ul>
                        {team2.map(character => (
                            <li key={character.id}>
                                {character.name} (Class: {character.charClass}, Level: {character.level})
                                <button onClick={() => removeFromTeam(character, team2, setTeam2)}>Remove from Team</button>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>

            <button onClick={handleSubmit}>Start Battle</button>
        </div>
    );
};

export default TeamSelectionPage;











// import React, { useState, useEffect } from 'react';
// import { useNavigate } from 'react-router-dom'; // Import navigate for redirecting

// const TeamSelectionPage = () => {
//     const [characters, setCharacters] = useState([]); // All characters available for selection
//     const [team1, setTeam1] = useState([]); // Characters for Team 1
//     const [team2, setTeam2] = useState([]); // Characters for Team 2
//     const navigate = useNavigate(); // For navigation to BattlePage

//     useEffect(() => {
//         // Fetch available characters when the component loads
//         fetch('http://localhost:8081/api/characters')
//             .then(response => response.json())
//             .then(data => setCharacters(data))
//             .catch(error => console.error('Failed to fetch characters:', error));
//     }, []);

//     // Add a character to a team (team1 or team2)
//     const addToTeam = (character, team, setTeam) => {
//         // Ensure no duplicate characters in a team
//         if (!team.includes(character)) {
//             setTeam([...team, character]);
//         }
//     };

//     // Remove a character from a team
//     const removeFromTeam = (character, team, setTeam) => {
//         setTeam(team.filter(c => c.id !== character.id));
//     };

//     const handleSubmit = () => {
//         if (team1.length === 0 || team2.length === 0) {
//             alert('Both teams must have at least one character!');
//             return;
//         }
        
//         // Submit the selected teams to the backend for battle simulation
//         fetch('http://localhost:8081/api/battle/team', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json',
//             },
//             body: JSON.stringify({ team1, team2 })
//         })
//         .then(response => response.json())
//         .then(data => {
//             // Navigate to the battle log page with the result
//             navigate('/battle', { state: { battleLog: data.battleLog } });
//         })
//         .catch(error => {
//             console.error('Error starting the battle:', error);
//         });
//     };

//     return (
//         <div>
//             <h1>Team Selection</h1>
//             <div style={{ display: 'flex', justifyContent: 'space-between' }}>
//                 {/* Available Characters */}
//                 <div>
//                     <h2>Available Characters</h2>
//                     <ul>
//                         {characters.map(character => (
//                             <li key={character.id}>
//                                 {character.name} (Class: {character.charClass}, Level: {character.level})
//                                 <button onClick={() => addToTeam(character, team1, setTeam1)}>Add to Team 1</button>
//                                 <button onClick={() => addToTeam(character, team2, setTeam2)}>Add to Team 2</button>
//                             </li>
//                         ))}
//                     </ul>
//                 </div>

//                 {/* Team 1 */}
//                 <div>
//                     <h2>Team 1</h2>
//                     <ul>
//                         {team1.map(character => (
//                             <li key={character.id}>
//                                 {character.name} (Class: {character.charClass}, Level: {character.level})
//                                 <button onClick={() => removeFromTeam(character, team1, setTeam1)}>Remove from Team</button>
//                             </li>
//                         ))}
//                     </ul>
//                 </div>

//                 {/* Team 2 */}
//                 <div>
//                     <h2>Team 2</h2>
//                     <ul>
//                         {team2.map(character => (
//                             <li key={character.id}>
//                                 {character.name} (Class: {character.charClass}, Level: {character.level})
//                                 <button onClick={() => removeFromTeam(character, team2, setTeam2)}>Remove from Team</button>
//                             </li>
//                         ))}
//                     </ul>
//                 </div>
//             </div>

//             <button onClick={handleSubmit}>Start Battle</button>
//         </div>
//     );
// };

// export default TeamSelectionPage;







































// import React, { useState, useEffect } from 'react';

// const TeamSelectionPage = ({ onTeamSubmit }) => {
//     const [characters, setCharacters] = useState([]);
//     const [team1, setTeam1] = useState([]);
//     const [team2, setTeam2] = useState([]);

//     useEffect(() => {
//         // Fetch all available characters from the backend
//         fetch('http://localhost:8081/api/characters')
//             .then(response => response.json())
//             .then(data => setCharacters(data))
//             .catch(error => console.error('Failed to fetch characters:', error));
//     }, []);

//     const handleTeamChange = (character, teamNumber) => {
//         if (teamNumber === 1) {
//             setTeam1([...team1, character]);
//         } else {
//             setTeam2([...team2, character]);
//         }
//     };

//     const handleRemoveFromTeam = (character, teamNumber) => {
//         if (teamNumber === 1) {
//             setTeam1(team1.filter(c => c.id !== character.id));
//         } else {
//             setTeam2(team2.filter(c => c.id !== character.id));
//         }
//     };

//     const handleSubmit = () => {
//         if (team1.length === 0 || team2.length === 0) {
//             alert('Both teams must have at least one character!');
//             return;
//         }
//         onTeamSubmit(team1, team2);
//     };

//     return (
//         <div>
//             <h1>Team Selection</h1>
//             <div className="team-container">
//                 <div>
//                     <h2>Available Characters</h2>
//                     <ul>
//                         {characters.map(character => (
//                             <li key={character.id}>
//                                 {character.name} - {character.charClass}
//                                 <button onClick={() => handleTeamChange(character, 1)}>Add to Team 1</button>
//                                 <button onClick={() => handleTeamChange(character, 2)}>Add to Team 2</button>
//                             </li>
//                         ))}
//                     </ul>
//                 </div>

//                 <div>
//                     <h2>Team 1</h2>
//                     <ul>
//                         {team1.map(character => (
//                             <li key={character.id}>
//                                 {character.name}
//                                 <button onClick={() => handleRemoveFromTeam(character, 1)}>Remove</button>
//                             </li>
//                         ))}
//                     </ul>
//                 </div>

//                 <div>
//                     <h2>Team 2</h2>
//                     <ul>
//                         {team2.map(character => (
//                             <li key={character.id}>
//                                 {character.name}
//                                 <button onClick={() => handleRemoveFromTeam(character, 2)}>Remove</button>
//                             </li>
//                         ))}
//                     </ul>
//                 </div>
//             </div>

//             <button onClick={handleSubmit}>Start Battle</button>
//         </div>
//     );
// };

// export default TeamSelectionPage;
