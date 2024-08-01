// Obtener el ID de la facultad desde la URL
const urlParams = new URLSearchParams(window.location.search);
const facultyId = urlParams.get('id');

// Cargar los datos de la facultad al cargar la página
fetch(`/api/faculties/${encodeURIComponent(facultyId)}`)
    .then(response => {
        if (!response.ok) throw new Error('Error al cargar la facultad');
        return response.json();
    })
    .then(faculty => {
        // Rellenar los campos de entrada con los datos de la facultad
        document.getElementById('name').value = faculty.name;
        document.getElementById('department').value = faculty.department;
        document.getElementById('email').value = faculty.email;
    })
    .catch(error => console.error('Error:', error));

// Manejar la actualización de la facultad al enviar el formulario
document.getElementById('editForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const department = document.getElementById('department').value;
    const email = document.getElementById('email').value;

    fetch(`/api/faculties/${encodeURIComponent(facultyId)}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, department, email })
    })
        .then(response => {
            if (response.ok) {
                alert('Facultad actualizada');
                window.location.href = 'faculties.html'; // Redirige a la lista de facultades
            } else {
                alert('Error al actualizar la facultad');
            }
        })
        .catch(error => console.error('Error:', error));
});

// Función para cancelar la edición y regresar a la lista de facultades
function cancelEdit() {
    window.location.href = 'faculties.html'; // Redirige a la lista de facultades
}
