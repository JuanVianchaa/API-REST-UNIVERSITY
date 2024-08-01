// Manejar la adición de nueva facultad al enviar el formulario
document.getElementById('addForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const department = document.getElementById('department').value;
    const email = document.getElementById('email').value;

    fetch('/api/faculties', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, department, email })
    })
        .then(response => {
            if (response.ok) {
                alert('Facultad agregada');
                window.location.href = 'faculties.html'; // Redirige a la lista de facultades
            } else {
                alert('Error al agregar la facultad');
            }
        })
        .catch(error => console.error('Error:', error));
});

// Función para cancelar la adición y regresar a la lista de facultades
function cancelAdd() {
    window.location.href = 'faculties.html'; // Redirige a la lista de facultades
}
