
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Dashboard() {
    const [user, setUser] = useState(null);
    const history = useNavigate();

    useEffect(() => {
        const userData = localStorage.getItem('user');
        if (userData) {
            setUser(JSON.parse(userData));
        } else {
            history('/');
        }
    }, [history]);

    const handleLogout = () => {
        localStorage.removeItem('user');
        history('/');
    };

    if (!user) {
        return <div>Loading...</div>;
    }

    return (
        <div className="d-flex justify-content-center align-items-center vh-100">
            <div className="border rounded-lg p-4" style={{width: '600px', height: 'auto'}}>
                <h2 className="mb-4 text-center">Welcome to Dashboard</h2>
                <div className="mb-4">
                    <p><strong>Name:</strong> {user.firstName} {user.lastName}</p>
                    <p><strong>Username:</strong> {user.userName}</p>
                    <p><strong>Email:</strong> {user.contact?.email}</p>
                    <p><strong>Phone:</strong> {user.contact?.phoneNumber}</p>
                    <p><strong>Role:</strong> {user.role}</p>
                    {user.address && (
                        <div>
                            <p><strong>Address:</strong></p>
                            <p>{user.address.street}, {user.address.municipality}</p>
                            <p>{user.address.province}, {user.address.postalCode}</p>
                            <p>{user.address.country}</p>
                        </div>
                    )}
                </div>
                <div className="text-center">
                    <button type="button" className="btn btn-primary mt-3" onClick={handleLogout}>
                        Logout
                    </button>
                </div>
            </div>
        </div>
    );
}

export default Dashboard;
