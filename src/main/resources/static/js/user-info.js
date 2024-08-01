// Cargar la información del usuario desde el almacenamiento local
const user = JSON.parse(localStorage.getItem('loggedInUser'));
if (user) {
    document.getElementById('userInfo').innerHTML = `
        <p><strong>Nombre:</strong> ${user.name}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <div class="password-container">
            <span class="password-label"><strong>Contraseña:</strong></span>
            <input type="password" id="passwordInput" class="password-input" value="${user.password}" readonly>
            <span id="eyeIcon" class="eye-icon">👁️</span>
        </div>
    `;

    // Lógica para mostrar/ocultar la contraseña
    const passwordInput = document.getElementById('passwordInput');
    const eyeIcon = document.getElementById('eyeIcon');

    eyeIcon.addEventListener('click', function() {
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            eyeIcon.textContent = "👁️‍🗨️"; // Cambia el icono a uno diferente si lo deseas
        } else {
            passwordInput.type = "password";
            eyeIcon.textContent = "👁️";
        }
    });

    // Lógica para eliminar usuario
    document.getElementById('deleteUserButton').addEventListener('click', function() {
        const passwordToDelete = prompt("Por favor, ingresa tu contraseña para confirmar la eliminación:");
        if (passwordToDelete === user.password) {
            fetch(`/api/users/${user.id}`, {
                method: 'DELETE',
            })
                .then(response => {
                    if (response.ok) {
                        alert('Usuario eliminado exitosamente');
                        localStorage.removeItem('loggedInUser');
                        window.location.href = 'index.html'; // Redirigir a la página de inicio
                    } else {
                        alert('Error al eliminar el usuario. Inténtalo de nuevo.');
                    }
                })
                .catch(error => console.error('Error:', error));
        } else {
            alert('Contraseña incorrecta. No se pudo eliminar el usuario.');
        }
    });
} else {
    alert('No has iniciado sesión. Redirigiendo al inicio...');
    window.location.href = 'index.html'; // Redirigir al inicio si no hay sesión activa
}
