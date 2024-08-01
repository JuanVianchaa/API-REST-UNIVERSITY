const user = JSON.parse(localStorage.getItem('loggedInUser'));
if (!user) {
    alert('No has iniciado sesión. Redirigiendo al inicio...');
    window.location.href = 'index.html';
}

function logout() {
    localStorage.removeItem('loggedInUser');
}
