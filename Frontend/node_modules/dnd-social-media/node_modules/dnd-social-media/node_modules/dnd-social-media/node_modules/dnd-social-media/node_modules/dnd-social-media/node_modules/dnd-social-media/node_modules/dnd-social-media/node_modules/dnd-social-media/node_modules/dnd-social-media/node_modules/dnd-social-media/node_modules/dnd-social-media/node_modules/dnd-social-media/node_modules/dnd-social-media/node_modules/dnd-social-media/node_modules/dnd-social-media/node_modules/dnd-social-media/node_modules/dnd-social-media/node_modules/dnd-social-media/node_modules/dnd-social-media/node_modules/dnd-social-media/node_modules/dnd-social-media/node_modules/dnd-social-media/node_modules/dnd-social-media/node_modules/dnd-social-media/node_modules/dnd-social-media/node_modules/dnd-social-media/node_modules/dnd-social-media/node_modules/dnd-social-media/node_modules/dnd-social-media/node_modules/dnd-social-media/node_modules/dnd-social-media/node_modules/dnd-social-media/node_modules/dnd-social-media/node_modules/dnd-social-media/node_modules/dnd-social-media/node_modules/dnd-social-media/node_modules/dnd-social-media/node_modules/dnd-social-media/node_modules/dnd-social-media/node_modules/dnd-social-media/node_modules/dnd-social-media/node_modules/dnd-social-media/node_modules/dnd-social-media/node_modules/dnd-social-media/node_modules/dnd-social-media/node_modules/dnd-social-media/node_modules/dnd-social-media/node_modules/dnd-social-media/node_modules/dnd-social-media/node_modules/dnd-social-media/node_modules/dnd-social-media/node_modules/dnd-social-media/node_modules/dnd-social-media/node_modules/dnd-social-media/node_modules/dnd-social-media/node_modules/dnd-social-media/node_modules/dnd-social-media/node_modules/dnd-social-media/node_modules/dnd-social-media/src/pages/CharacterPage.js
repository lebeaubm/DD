import React, { useEffect, useState } from 'react';
import CharacterForm from '../components/CharacterForm';

function CharacterPage() {
  const [characters, setCharacters] = useState([]);

  // Function to fetch existing characters from the backend
  const fetchCharacters = async () => {
    try {
      const response = await fetch('http://localhost:8081/api/characters/user/testUser'); // Replace 'testUser' with the logged-in username dynamically
      if (!response.ok) {
        throw new Error('Failed to fetch characters');
      }
      const data = await response.json();
      setCharacters(data);
    } catch (error) {
      console.error('Failed to fetch characters:', error);
    }
  };

  // Fetch characters when the component mounts
  useEffect(() => {
    fetchCharacters();
  }, []);

  const handleCharacterCreated = (newCharacter) => {
    // Append the newly created character to the existing list
    setCharacters([...characters, newCharacter]);
  };

  return (
    <div>
      <h1>Character Creation</h1>
      <CharacterForm onCharacterCreated={handleCharacterCreated} />
      <div>
        <h2>Character List</h2>
        <ul>
          {characters.map((character) => (
            <li key={character.id}>
              {character.name} - {character.race} - {character.charClass} - Level {character.level}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default CharacterPage;




