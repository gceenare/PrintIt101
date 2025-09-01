
import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import {
    MDBContainer,
    MDBInput,
    MDBBtn,
} from 'mdb-react-ui-kit';

function SignupPage() {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [userName, setUserName] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');
    const [phone, setPhone] = useState('');
    const [street, setStreet] = useState('');
    const [municipality, setMunicipality] = useState('');
    const [province, setProvince] = useState('');
    const [postalCode, setPostalCode] = useState('');
    const [country, setCountry] = useState('');
    const [error, setError] = useState('');
    const history = useNavigate();

    const handleSignup = async () => {
        try {
            // Check for empty fields
            if (!firstName || !lastName || !userName || !password || !confirmPassword || !email || !phone || !street || !municipality || !province || !postalCode || !country) {
                setError('Please fill in all fields.');
                return;
            }

            if (password !== confirmPassword) {
                setError("Passwords do not match");
                return;
            }

            const registerRequest = {
                firstName,
                lastName,
                userName,
                password,
                contact: {
                    email,
                    phone
                },
                address: {
                    street,
                    municipality,
                    province,
                    postalCode,
                    country
                }
            };

            const response = await axios.post('http://0.0.0.0:8080/api/auth/register', registerRequest);
            console.log('Registration successful:', response.data);
            history('/');
        } catch (error) {
            console.error('Signup failed:', error.response ? error.response.data : error.message);
            setError(error.response?.data?.message || 'Registration failed. Please try again.');
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '600px', height: 'auto'}}>
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Sign Up Page</h2>
                    {error && <p className="text-danger">{error}</p>}
                    
                    <MDBInput wrapperClass='mb-3' id='firstName' placeholder="First Name" value={firstName} type='text'
                              onChange={(e) => setFirstName(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' id='lastName' placeholder="Last Name" value={lastName} type='text'
                              onChange={(e) => setLastName(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' id='userName' placeholder="Username" value={userName} type='text'
                              onChange={(e) => setUserName(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Email Address' id='email' value={email} type='email'
                              onChange={(e) => setEmail(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Phone Number' id='phone' value={phone} type='text'
                              onChange={(e) => setPhone(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Password' id='password' type='password' value={password}
                              onChange={(e) => setPassword(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Confirm Password' id='confirmPassword' type='password'
                              value={confirmPassword}
                              onChange={(e) => setConfirmPassword(e.target.value)}/>
                    
                    <h5 className="mb-3">Address Information</h5>
                    <MDBInput wrapperClass='mb-3' placeholder='Street' id='street' value={street} type='text'
                              onChange={(e) => setStreet(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Municipality' id='municipality' value={municipality} type='text'
                              onChange={(e) => setMunicipality(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Province' id='province' value={province} type='text'
                              onChange={(e) => setProvince(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Postal Code' id='postalCode' value={postalCode} type='text'
                              onChange={(e) => setPostalCode(e.target.value)}/>
                    <MDBInput wrapperClass='mb-3' placeholder='Country' id='country' value={country} type='text'
                              onChange={(e) => setCountry(e.target.value)}/>

                    <button className="mb-4 d-block mx-auto fixed-action-btn btn-primary"
                            style={{height: '40px', width: '100%'}}
                            onClick={handleSignup}>Sign Up
                    </button>

                    <div className="text-center">
                        <p>Already Registered? <a href="/">Login</a></p>
                    </div>

                </MDBContainer>
            </div>
        </div>
    );
}

export default SignupPage;
