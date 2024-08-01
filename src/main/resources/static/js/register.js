document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, email, password })
    })
        .then(response => {
            if (response.ok) {
                alert('Usuario registrado exitosamente');
                window.location.href = 'index.html';
            } else {
                alert('Error al registrar el usuario');
            }
        })
        .catch(error => console.error('Error:', error));
});
