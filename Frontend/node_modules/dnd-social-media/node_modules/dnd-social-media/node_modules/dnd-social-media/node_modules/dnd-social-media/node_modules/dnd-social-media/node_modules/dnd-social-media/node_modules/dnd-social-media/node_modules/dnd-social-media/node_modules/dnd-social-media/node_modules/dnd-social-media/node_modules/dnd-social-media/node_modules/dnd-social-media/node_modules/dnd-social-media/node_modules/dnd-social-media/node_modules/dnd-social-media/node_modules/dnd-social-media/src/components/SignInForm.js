import React, { useState } from 'react';

function SignInForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [message, setMessage] = useState('');

    const handleSignIn = (event) => {
        event.preventDefault();

        fetch(`/api/signin?username=${encodeURIComponent(username)}&password=${encodeURIComponent(password)}`, {
            method: 'POST',
        })
        .then(response => {
            if (response.ok) {
                return response.text();
            } else {
                throw new Error('Sign-in failed');
            }
        })
        .then(data => {
            setMessage('Sign-in successful!');
        })
        .catch(error => {
            setMessage('Sign-in failed. Please check your credentials and try again.');
            console.error('Error:', error);
        });
    };

    return (
        <div>
            <h2>Sign In</h2>
            <form onSubmit={handleSignIn}>
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
                    <label>Password:</label>
                    <input 
                        type="password" 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        required 
                    />
                </div>
                <button type="submit">Sign In</button>
            </form>
            {message && <p>{message}</p>}
        </div>
    );
}

export default SignInForm;


