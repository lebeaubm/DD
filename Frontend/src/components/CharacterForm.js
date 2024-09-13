import React, { useState } from "react";

const CharacterForm = ({ onCharacterCreate }) => {
  const [name, setName] = useState("");
  const [race, setRace] = useState("");
  const [charClass, setCharClass] = useState("");
  const [level, setLevel] = useState(1);
  const [username, setUsername] = useState(""); // Add a username field

  // Other fields for the character stats
  const [strength, setStrength] = useState(10);
  const [dexterity, setDexterity] = useState(10);
  const [constitution, setConstitution] = useState(10);
  const [intelligence, setIntelligence] = useState(10);
  const [wisdom, setWisdom] = useState(10);
  const [charisma, setCharisma] = useState(10);
  const [hitPoints, setHitPoints] = useState(10);
  const [armorClass, setArmorClass] = useState(10);
  const [initiative, setInitiative] = useState(0);
  const [background, setBackground] = useState("");
  const [equipment, setEquipment] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const characterData = {
      name,
      race,
      charClass,
      level,
      username, // Include the username in the request
      strength,
      dexterity,
      constitution,
      intelligence,
      wisdom,
      charisma,
      hitPoints,
      armorClass,
      initiative,
      background,
      equipment,
    };

    try {
      const response = await fetch("http://localhost:8081/api/characters/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(characterData),
      });

      if (response.ok) {
        const createdCharacter = await response.json();
        onCharacterCreate(createdCharacter); // Pass the created character back to the parent component
      } else {
        console.error("Failed to create character");
      }
    } catch (error) {
      console.error("Error creating character: ", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Username:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
      </div>
      <div>
        <label>Character Name:</label>
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
          onChange={(e) => setLevel(parseInt(e.target.value))}
          required
        />
      </div>
      {/* Include other fields like strength, dexterity, etc. */}
      <div>
        <label>Strength:</label>
        <input
          type="number"
          value={strength}
          onChange={(e) => setStrength(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Dexterity:</label>
        <input
          type="number"
          value={dexterity}
          onChange={(e) => setDexterity(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Constitution:</label>
        <input
          type="number"
          value={constitution}
          onChange={(e) => setConstitution(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Intelligence:</label>
        <input
          type="number"
          value={intelligence}
          onChange={(e) => setIntelligence(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Wisdom:</label>
        <input
          type="number"
          value={wisdom}
          onChange={(e) => setWisdom(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Charisma:</label>
        <input
          type="number"
          value={charisma}
          onChange={(e) => setCharisma(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Hit Points:</label>
        <input
          type="number"
          value={hitPoints}
          onChange={(e) => setHitPoints(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Armor Class:</label>
        <input
          type="number"
          value={armorClass}
          onChange={(e) => setArmorClass(parseInt(e.target.value))}
          required
        />
      </div>
      <div>
        <label>Initiative:</label>
        <input
          type="number"
          value={initiative}
          onChange={(e) => setInitiative(parseInt(e.target.value))}
          required
        />
      </div>








      {/* Add more fields for other stats */}
      <button type="submit">Create Character</button>
    </form>
  );
};

export default CharacterForm;






// import React, { useState } from 'react';

// const CharacterForm = ({ onCharacterCreate }) => {
//     const [formData, setFormData] = useState({
//         name: '',
//         race: '',
//         charClass: '',
//         level: '',
//         strength: '',
//         dexterity: '',
//         constitution: '',
//         intelligence: '',
//         wisdom: '',
//         charisma: '',
//         hitPoints: '',
//         armorClass: '',
//         initiative: '',
//         background: '',
//         equipment: ''
//     });

//     const handleChange = (e) => {
//         setFormData({ ...formData, [e.target.name]: e.target.value });
//     };

//     const handleSubmit = async (e) => {
//         e.preventDefault();
//         const response = await fetch('http://localhost:8081/api/characters/create', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify(formData),
//         });
//         const data = await response.json();
//         onCharacterCreate(data);  // Pass the created character to the parent component
//     };

//     return (
//         <form onSubmit={handleSubmit}>
//             {/* Basic fields */}
//             <input type="text" name="name" value={formData.name} onChange={handleChange} placeholder="Name" required />
//             <input type="text" name="race" value={formData.race} onChange={handleChange} placeholder="Race" required />
//             <input type="text" name="charClass" value={formData.charClass} onChange={handleChange} placeholder="Class" required />
//             <input type="number" name="level" value={formData.level} onChange={handleChange} placeholder="Level" required />

//             {/* Stats */}
//             <input type="number" name="strength" value={formData.strength} onChange={handleChange} placeholder="Strength" required />
//             <input type="number" name="dexterity" value={formData.dexterity} onChange={handleChange} placeholder="Dexterity" required />
//             <input type="number" name="constitution" value={formData.constitution} onChange={handleChange} placeholder="Constitution" required />
//             <input type="number" name="intelligence" value={formData.intelligence} onChange={handleChange} placeholder="Intelligence" required />
//             <input type="number" name="wisdom" value={formData.wisdom} onChange={handleChange} placeholder="Wisdom" required />
//             <input type="number" name="charisma" value={formData.charisma} onChange={handleChange} placeholder="Charisma" required />

//             {/* Other fields */}
//             <input type="number" name="hitPoints" value={formData.hitPoints} onChange={handleChange} placeholder="Hit Points" required />
//             <input type="number" name="armorClass" value={formData.armorClass} onChange={handleChange} placeholder="Armor Class" required />
//             <input type="number" name="initiative" value={formData.initiative} onChange={handleChange} placeholder="Initiative" required />
//             <input type="text" name="background" value={formData.background} onChange={handleChange} placeholder="Background" required />
//             <input type="text" name="equipment" value={formData.equipment} onChange={handleChange} placeholder="Equipment" required />

//             <button type="submit">Create Character</button>
//         </form>
//     );
// };

// export default CharacterForm;

































// import React, { useState } from 'react';

// function CharacterForm({ onCharacterCreated }) {
//   const [name, setName] = useState('');
//   const [race, setRace] = useState('');
//   const [charClass, setCharClass] = useState('');
//   const [level, setLevel] = useState(1);

//   const handleSubmit = async (event) => {
//     event.preventDefault();

//     const characterData = {
//       name: name,
//       race: race,
//       charClass: charClass,
//       level: parseInt(level),
//       username: "testUser" // You can replace this with a dynamic username later
//     };

//     try {
//       const response = await fetch('http://localhost:8081/api/characters/create', {
//         method: 'POST',
//         headers: {
//           'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(characterData),
//       });

//       if (response.ok) {
//         const newCharacter = await response.json();
//         console.log('Character created:', newCharacter);
//         if (onCharacterCreated) {
//           onCharacterCreated(newCharacter); // Callback to update character list if necessary
//         }
//       } else {
//         console.error('Failed to create character:', response.status);
//       }
//     } catch (error) {
//       console.error('Error creating character:', error);
//     }
//   };

//   return (
//     <form onSubmit={handleSubmit}>
//       <div>
//         <label>Name:</label>
//         <input
//           type="text"
//           value={name}
//           onChange={(e) => setName(e.target.value)}
//           required
//         />
//       </div>
//       <div>
//         <label>Race:</label>
//         <input
//           type="text"
//           value={race}
//           onChange={(e) => setRace(e.target.value)}
//           required
//         />
//       </div>
//       <div>
//         <label>Class:</label>
//         <input
//           type="text"
//           value={charClass}
//           onChange={(e) => setCharClass(e.target.value)}
//           required
//         />
//       </div>
//       <div>
//         <label>Level:</label>
//         <input
//           type="number"
//           value={level}
//           onChange={(e) => setLevel(e.target.value)}
//           required
//         />
//       </div>
//       <button type="submit">Create Character</button>
//     </form>
//   );
// }

// export default CharacterForm;









