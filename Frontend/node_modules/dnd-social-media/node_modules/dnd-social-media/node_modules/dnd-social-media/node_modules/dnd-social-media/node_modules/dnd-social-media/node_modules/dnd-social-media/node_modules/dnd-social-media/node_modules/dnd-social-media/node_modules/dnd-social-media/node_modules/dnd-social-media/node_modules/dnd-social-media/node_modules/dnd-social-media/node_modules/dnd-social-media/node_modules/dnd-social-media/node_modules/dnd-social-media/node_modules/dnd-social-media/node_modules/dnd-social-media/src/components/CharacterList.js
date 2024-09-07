import React, { useEffect, useState } from 'react';

function CharacterList({ username }) {
    const [characters, setCharacters] = useState([]);

    useEffect(() => {
        if (username) {
            fetch(`/api/characters/user/${username}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to fetch characters');
                    }
                    return response.json();
                })
                .then(data => setCharacters(Array.isArray(data) ? data : []))
                .catch(error => console.error('Error:', error));
        }
    }, [username]);

    return (
        <div>
            <h2>My Characters</h2>
            {characters.length === 0 ? (
                <p>No characters found.</p>
            ) : (
                <ul>
                    {characters.map(character => (
                        <li key={character.id}>{character.name} - {character.className} ({character.race}) - Level: {character.level}</li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default CharacterList;

