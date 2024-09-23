import React from 'react';

const BattleStartForm = () => {
  const handleStartBattle = () => {
    fetch('/api/battle/start', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        team1: [
          { id: 1, name: "Gandalf", race: "Maia", charClass: "Wizard", level: 20 },
          { id: 2, name: "Frodo", race: "Hobbit", charClass: "Rogue", level: 10 }
        ],
        team2: [
          { id: 3, name: "Sauron", race: "Maia", charClass: "Sorcerer", level: 20 },
          { id: 4, name: "Saruman", race: "Maia", charClass: "Wizard", level: 15 }
        ]
      })
    }).then(response => {
      if (response.ok) {
        console.log('Battle started');
      } else {
        console.log('Error starting battle');
      }
    });
  };

  return (
    <div>
      <h1>Start a Battle</h1>
      <button onClick={handleStartBattle}>Start Battle</button>
    </div>
  );
};

export default BattleStartForm;
