document.addEventListener('DOMContentLoaded', () => {
    requireAuth('DOCTOR');
    loadAllAppointments();
});

async function loadAllAppointments() {
    try {
        const res = await fetch(`${API_BASE}/appointments/all`);
        const data = await res.json();
        
        const tbody = document.getElementById('allAppointmentsBody');
        tbody.innerHTML = '';

        if (data.length === 0) {
            tbody.innerHTML = `<tr><td colspan="6" style="text-align: center;">No appointments found.</td></tr>`;
            return;
        }

        data.forEach(app => {
            let statusBadge = app.status === 'PENDING' ? 'badge-warning' : 
                              (app.status === 'CONFIRMED' ? 'badge-success' : 'badge-danger');

            let actionBtns = app.status === 'PENDING' 
                ? `<button class="btn btn-success" style="padding:0.3rem 0.6rem;font-size:0.8rem;" onclick="updateStatus(${app.id}, 'CONFIRMED')">Confirm</button>
                   <button class="btn btn-danger" style="padding:0.3rem 0.6rem;font-size:0.8rem;" onclick="updateStatus(${app.id}, 'CANCELLED')">Cancel</button>`
                : `<span style="color:var(--text-muted);font-size:0.8rem">No Actions</span>`;

            tbody.innerHTML += `
                <tr>
                    <td><strong>${app.patientName}</strong></td>
                    <td>${app.patientId}</td>
                    <td>${formatDate(app.appointmentDate)}</td>
                    <td>${app.reason}</td>
                    <td><span class="badge ${statusBadge}">${app.status}</span></td>
                    <td>${actionBtns}</td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading appointments:', error);
    }
}

async function updateStatus(id, newStatus) {
    try {
        const res = await fetch(`${API_BASE}/appointments/${id}/status`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(newStatus)
        });
        if (res.ok) {
            loadAllAppointments(); 
        } else {
            alert('Failed to update status');
        }
    } catch (err) {
        console.error(err);
        alert('API error');
    }
}
