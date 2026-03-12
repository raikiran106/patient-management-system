// API Base URL
const API_BASE = '/api';

// Authentication Logic
async function handleLogin(role) {
    const userError = document.getElementById('loginError');
    userError.classList.add('hidden');
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    try {
        const response = await fetch(`${API_BASE}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password, role })
        });
        
        const data = await response.json();
        
        if (data.success) {
            sessionStorage.setItem('userRole', role);
            if (role === 'PATIENT') {
                sessionStorage.setItem('patientData', JSON.stringify(data.patient));
                window.location.href = 'patient-dashboard.html';
            } else {
                sessionStorage.setItem('username', username);
                window.location.href = 'doctor-dashboard.html';
            }
        } else {
            userError.innerText = data.message || 'Invalid credentials';
            userError.classList.remove('hidden');
        }
    } catch (err) {
        userError.innerText = 'Server connection failed';
        userError.classList.remove('hidden');
    }
}

// Check Auth state on protected pages
function requireAuth(role) {
    const currentRole = sessionStorage.getItem('userRole');
    if (!currentRole || currentRole !== role) {
        window.location.href = 'index.html';
    }
}

function logout() {
    sessionStorage.clear();
    window.location.href = 'index.html';
}

// General Utilities
function formatDate(dateStr) {
    if (!dateStr) return 'N/A';
    const d = new Date(dateStr);
    return d.toLocaleDateString() + ' ' + d.toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'});
}

function toggleModal(modalId) {
    const overlay = document.getElementById(`${modalId}-overlay`);
    if(overlay) {
        overlay.classList.toggle('active');
    }
}
