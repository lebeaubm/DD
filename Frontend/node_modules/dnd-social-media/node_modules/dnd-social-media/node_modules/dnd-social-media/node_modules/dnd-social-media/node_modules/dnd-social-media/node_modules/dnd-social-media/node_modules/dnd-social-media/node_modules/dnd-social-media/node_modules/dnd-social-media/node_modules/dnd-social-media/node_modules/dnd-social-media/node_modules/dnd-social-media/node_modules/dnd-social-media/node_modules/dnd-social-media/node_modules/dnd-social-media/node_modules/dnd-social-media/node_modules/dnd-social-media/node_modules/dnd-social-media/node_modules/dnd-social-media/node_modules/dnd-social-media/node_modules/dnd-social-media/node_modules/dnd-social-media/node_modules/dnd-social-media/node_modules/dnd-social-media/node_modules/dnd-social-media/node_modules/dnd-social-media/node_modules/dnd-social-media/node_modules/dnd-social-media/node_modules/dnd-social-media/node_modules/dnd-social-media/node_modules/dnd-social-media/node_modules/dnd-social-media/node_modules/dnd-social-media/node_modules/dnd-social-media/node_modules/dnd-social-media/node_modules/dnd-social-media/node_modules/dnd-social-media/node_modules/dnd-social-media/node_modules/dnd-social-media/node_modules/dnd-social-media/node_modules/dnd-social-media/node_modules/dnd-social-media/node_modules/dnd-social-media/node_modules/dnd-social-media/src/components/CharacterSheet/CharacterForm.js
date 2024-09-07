import React, { useState } from 'react';

function CharacterForm({ username }) {
    const [name, setName] = useState('');
    const [race, setRace] = useState('');
    const [className, setClassName] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();
        fetch('/api/characters/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, name, race, className }),
        })
        .then(response => response.json())
        .then(data => console.log('Character created:', data));
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Name:</label>
                <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
            </div>
            <div>
                <label>Race:</label>
                <input type="text" value={race} onChange={(e) => setRace(e.target.value)} />
            </div>
            <div>
                <label>Class:</label>
                <input type="text" value={className} onChange={(e) => setClassName(e.target.value)} />
            </div>
            <button type="submit">Create Character</button>
        </form>
    );
}

export default CharacterForm;
