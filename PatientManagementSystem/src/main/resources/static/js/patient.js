document.addEventListener('DOMContentLoaded', () => {
    requireAuth('PATIENT');
    
    // Load Patient Data
    const patientStr = sessionStorage.getItem('patientData');
    if (!patientStr) return;
    
    const patient = JSON.parse(patientStr);
    document.getElementById('welcomeText').innerText = `Welcome, ${patient.name}`;
    document.getElementById('patientIdBadge').innerText = `ID: ${patient.patientId}`;
    document.getElementById('avatarInitial').innerText = patient.name.charAt(0).toUpperCase();

    loadAppointments(patient.patientId);

    // Form submission
    document.getElementById('bookForm').addEventListener('submit', async (e) => {
        e.preventDefault();
        const date = document.getElementById('appointmentDate').value;
        const doctor = document.getElementById('doctorName').value;
        const reason = document.getElementById('reason').value;

        try {
            const res = await fetch(`${API_BASE}/appointments/book`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    patientId: patient.patientId,
                    patientName: patient.name,
                    doctorName: doctor,
                    appointmentDate: date,
                    reason: reason
                })
            });
            if (res.ok) {
                toggleModal('bookModal');
                document.getElementById('bookForm').reset();
                loadAppointments(patient.patientId);
            }
        } catch (error) {
            console.error('Failed to book appointment:', error);
            alert('Failed to book. Try again.');
        }
    });
});

async function loadAppointments(patientId) {
    try {
        const res = await fetch(`${API_BASE}/appointments/${patientId}`);
        const data = await res.json();
        
        document.getElementById('appointmentCount').innerText = data.length || 0;
        const tbody = document.getElementById('appointmentTableBody');
        tbody.innerHTML = '';

        if (data.length === 0) {
            tbody.innerHTML = `<tr><td colspan="4" style="text-align: center;">No appointments found.</td></tr>`;
            return;
        }

        data.forEach(app => {
            let statusBadge = app.status === 'PENDING' ? 'badge-warning' : 
                              (app.status === 'CONFIRMED' ? 'badge-success' : 'badge-danger');

            tbody.innerHTML += `
                <tr>
                    <td>${formatDate(app.appointmentDate)}</td>
                    <td>${app.doctorName || 'Assigned Doctor'}</td>
                    <td>${app.reason}</td>
                    <td><span class="badge ${statusBadge}">${app.status}</span></td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error loading appointments:', error);
    }
}
