import React, { useState, useEffect } from 'react';

function BattleSimulator() {
    const [characters, setCharacters] = useState([]);
    const [char1, setChar1] = useState("");
    const [char2, setChar2] = useState("");
    const [result, setResult] = useState("");

    useEffect(() => {
        // Fetch characters to populate dropdown
        fetch('http://localhost:8081/api/characters')
            .then(response => response.json())
            .then(data => setCharacters(data))
            .catch(error => console.error("Failed to fetch characters: ", error));
    }, []);

    const handleBattle = () => {
        fetch(`http://localhost:8081/api/battle/start?characterId1=${char1}&characterId2=${char2}`, {
            method: 'POST',
        })
        .then(response => response.text())
        .then(data => setResult(data))
        .catch(error => console.error("Battle failed: ", error));
    };

    return (
        <div>
            <h1>Battle Simulator</h1>
            <div>
                <label>Character 1:</label>
                <select value={char1} onChange={e => setChar1(e.target.value)}>
                    <option value="">Select Character 1</option>
                    {characters.map((char) => (
                        <option key={char.id} value={char.id}>{char.name}</option>
                    ))}
                </select>
            </div>

            <div>
                <label>Character 2:</label>
                <select value={char2} onChange={e => setChar2(e.target.value)}>
                    <option value="">Select Character 2</option>
                    {characters.map((char) => (
                        <option key={char.id} value={char.id}>{char.name}</option>
                    ))}
                </select>
            </div>

            <button onClick={handleBattle}>Start Battle</button>

            {result && (
                <div>
                    <h2>Battle Result:</h2>
                    <p>{result}</p>
                </div>
            )}
        </div>
    );
}

export default BattleSimulator;
