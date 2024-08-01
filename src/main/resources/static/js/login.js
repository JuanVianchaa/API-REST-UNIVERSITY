document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    fetch('/api/users/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ email, password })
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(text || 'Email o contraseña incorrectos.');
                });
            }
            return response.json();
        })
        .then(data => {
            localStorage.setItem('loggedInUser', JSON.stringify(data));
            window.location.href = 'menu.html';
        })
        .catch(error => {
            alert(error.message);
        });
});
