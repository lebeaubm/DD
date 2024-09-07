import React, { useState } from 'react';

function CharacterForm({ onCharacterCreated }) {
  const [name, setName] = useState('');
  const [race, setRace] = useState('');
  const [charClass, setCharClass] = useState('');
  const [level, setLevel] = useState(1);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const characterData = {
      name: name,
      race: race,
      charClass: charClass,
      level: parseInt(level),
      username: "testUser" // You can replace this with a dynamic username later
    };

    try {
      const response = await fetch('http://localhost:8081/api/characters/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(characterData),
      });

      if (response.ok) {
        const newCharacter = await response.json();
        console.log('Character created:', newCharacter);
        if (onCharacterCreated) {
          onCharacterCreated(newCharacter); // Callback to update character list if necessary
        }
      } else {
        console.error('Failed to create character:', response.status);
      }
    } catch (error) {
      console.error('Error creating character:', error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name:</label>
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Race:</label>
        <input
          type="text"
          value={race}
          onChange={(e) => setRace(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Class:</label>
        <input
          type="text"
          value={charClass}
          onChange={(e) => setCharClass(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Level:</label>
        <input
          type="number"
          value={level}
          onChange={(e) => setLevel(e.target.value)}
          required
        />
      </div>
      <button type="submit">Create Character</button>
    </form>
  );
}

export default CharacterForm;






// import React, { useState } from 'react';

// function CharacterForm({ onCharacterCreated }) {
//     const [name, setName] = useState('');
//     const [race, setRace] = useState('');
//     const [charClass, setCharClass] = useState('');
//     const [level, setLevel] = useState('');
//     const [username, setUsername] = useState(''); // Assuming you have the username stored somewhere

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         try {
//             const response = await fetch('http://localhost:3000/api/characters/create', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json',
//                 },
//                 body: JSON.stringify({ name, race, charClass, level, username }),
//             });

//             if (response.ok) {
//                 const character = await response.json();
//                 onCharacterCreated(character);
//             } else {
//                 console.error('Error:', await response.json());
//             }
//         } catch (error) {
//             console.error('Error:', error);
//         }
//     };

//     return (
//         <form onSubmit={handleSubmit}>
//             <input
//                 type="text"
//                 placeholder="Character Name"
//                 value={name}
//                 onChange={(e) => setName(e.target.value)}
//                 required
//             />
//             <input
//                 type="text"
//                 placeholder="Race"
//                 value={race}
//                 onChange={(e) => setRace(e.target.value)}
//                 required
//             />
//             <input
//                 type="text"
//                 placeholder="Class"
//                 value={charClass}
//                 onChange={(e) => setCharClass(e.target.value)}
//                 required
//             />
//             <input
//                 type="number"
//                 placeholder="Level"
//                 value={level}
//                 onChange={(e) => setLevel(e.target.value)}
//                 required
//             />
//             <input
//                 type="text"
//                 placeholder="Username"
//                 value={username}
//                 onChange={(e) => setUsername(e.target.value)}
//                 required
//             />
//             <button type="submit">Create Character</button>
//         </form>
//     );
// }

// export default CharacterForm;



