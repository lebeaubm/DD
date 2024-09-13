
import React, { useState } from "react";
import CharacterForm from '../components/CharacterForm';

const CharacterPage = () => {
  const [characters, setCharacters] = useState([]);

  // This function will handle character creation on the parent side
  const handleCharacterCreate = (newCharacter) => {
    setCharacters([...characters, newCharacter]); // Update state with the new character
    console.log("Character Created: ", newCharacter);
  };

  return (
    <div>
      <h1>Create a New Character</h1>
      <CharacterForm onCharacterCreate={handleCharacterCreate} /> {/* Pass the function as a prop */}
      {/* Render existing characters */}
      <ul>
        {characters.map((char) => (
          <li key={char.id}>{char.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default CharacterPage;






// import React, { useEffect, useState } from 'react';
// import CharacterForm from '../components/CharacterForm';

// function CharacterPage() {
//   const [characters, setCharacters] = useState([]);

//   // Function to fetch existing characters from the backend
//   const fetchCharacters = async () => {
//     try {
//       const response = await fetch('http://localhost:8081/api/characters/user/testUser'); // Replace 'testUser' with the logged-in username dynamically
//       if (!response.ok) {
//         throw new Error('Failed to fetch characters');
//       }
//       const data = await response.json();
//       setCharacters(data);
//     } catch (error) {
//       console.error('Failed to fetch characters:', error);
//     }
//   };

//   // Fetch characters when the component mounts
//   useEffect(() => {
//     fetchCharacters();
//   }, []);

//   const handleCharacterCreated = (newCharacter) => {
//     // Append the newly created character to the existing list
//     setCharacters([...characters, newCharacter]);
//   };

//   return (
//     <div>
//       <h1>Character Creation</h1>
//       <CharacterForm onCharacterCreated={handleCharacterCreated} />
//       <div>
//         <h2>Character List</h2>
//         <ul>
//           {characters.map((character) => (
//             <li key={character.id}>
//               {character.name} - {character.race} - {character.charClass} - Level {character.level}
//             </li>
//           ))}
//         </ul>
//       </div>
//     </div>
//   );
// }

// export default CharacterPage;




