import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const CharacterDetail = () => {
  const { id } = useParams(); // Fetch the id from the URL
  const [character, setCharacter] = useState(null);

  useEffect(() => {
    const fetchCharacter = async () => {
      try {
        const response = await fetch(`http://localhost:8081/api/characters/${id}`);
        const data = await response.json();
        setCharacter(data);
      } catch (error) {
        console.error("Error fetching character details:", error);
      }
    };

    fetchCharacter();
  }, [id]);

  if (!character) {
    return <div>Loading character details...</div>;
  }

  return (
    <div>
      <h2>{character.name}'s Character Sheet</h2>
      <p><strong>Race:</strong> {character.race}</p>
      <p><strong>Class:</strong> {character.charClass}</p>
      <p><strong>Level:</strong> {character.level}</p>
      <h3>Stats</h3>
      <ul>
        <li><strong>Strength:</strong> {character.strength}</li>
        <li><strong>Dexterity:</strong> {character.dexterity}</li>
        <li><strong>Constitution:</strong> {character.constitution}</li>
        <li><strong>Intelligence:</strong> {character.intelligence}</li>
        <li><strong>Wisdom:</strong> {character.wisdom}</li>
        <li><strong>Charisma:</strong> {character.charisma}</li>
      </ul>
      <h3>Other Info</h3>
      <p><strong>Hit Points:</strong> {character.hitPoints}</p>
      <p><strong>Armor Class:</strong> {character.armorClass}</p>
      <p><strong>Initiative:</strong> {character.initiative}</p>
      <p><strong>Background:</strong> {character.background}</p>
      <p><strong>Equipment:</strong> {character.equipment}</p>
    </div>
  );
};

export default CharacterDetail;
