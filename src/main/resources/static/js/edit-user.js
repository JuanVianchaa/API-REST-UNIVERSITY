// Obtener el ID del usuario desde la URL
const urlParams = new URLSearchParams(window.location.search);
const userId = urlParams.get('id');

// Cargar los datos del usuario al cargar la página
fetch(`/api/users/${userId}`)
    .then(response => {
        if (!response.ok) throw new Error('Error al cargar el usuario');
        return response.json();
    })
    .then(user => {
        // Rellenar los campos de entrada con los datos del usuario
        document.getElementById('name').value = user.name; // Asigna el nombre al campo
        document.getElementById('email').value = user.email; // Asigna el email al campo
    })
    .catch(error => console.error('Error:', error));

// Manejar la actualización del usuario al enviar el formulario
document.getElementById('editForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value; // Obtiene el valor del nombre
    const email = document.getElementById('email').value; // Obtiene el valor del email

    fetch(`/api/users/${userId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, email }) // Crea el cuerpo de la solicitud con los datos actualizados
    })
        .then(response => {
            if (response.ok) {
                alert('Usuario actualizado'); // Muestra un mensaje de éxito
                window.location.href = 'users.html'; // Redirige a la lista de usuarios
            } else {
                alert('Error al actualizar el usuario'); // Muestra un mensaje de error
            }
        })
        .catch(error => console.error('Error:', error));
});

// Función para cancelar la edición y regresar a la lista de usuarios
function cancelEdit() {
    window.location.href = 'users.html'; // Redirige a la lista de usuarios
}
