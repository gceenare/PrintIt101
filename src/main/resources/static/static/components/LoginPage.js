
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {
    MDBContainer,
    MDBInput,
    MDBBtn,
} from 'mdb-react-ui-kit';

function LoginPage() {
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useNavigate();

    const handleLogin = async () => {
        try {
            if (!userName || !password) {
                setError('Please enter both username and password.');
                return;
            }

            const response = await axios.post('http://0.0.0.0:8080/api/auth/login', { 
                userName, 
                password 
            });
            console.log('Login successful:', response.data);
            localStorage.setItem('user', JSON.stringify(response.data));
            history('/dashboard');
        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
            setError(error.response?.data?.message || 'Invalid username or password.');
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{ width: '500px', height: 'auto' }}>
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Login Page</h2>
                    <MDBInput 
                        wrapperClass='mb-4' 
                        placeholder='Username' 
                        id='userName' 
                        value={userName} 
                        type='text' 
                        onChange={(e) => setUserName(e.target.value)} 
                    />
                    <MDBInput 
                        wrapperClass='mb-4' 
                        placeholder='Password' 
                        id='password' 
                        type='password' 
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                    />
                    {error && <p className="text-danger">{error}</p>}
                    <button 
                        className="mb-4 d-block btn-primary" 
                        style={{ height:'50px',width: '100%' }} 
                        onClick={handleLogin}
                    >
                        Sign in
                    </button>
                    <div className="text-center">
                        <p>Not a member? <a href="/signup">Register</a></p>
                    </div>
                </MDBContainer>
            </div>
        </div>
    );
}

export default LoginPage;
