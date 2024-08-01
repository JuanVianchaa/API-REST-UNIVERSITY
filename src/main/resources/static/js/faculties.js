fetch('/api/faculties')
    .then(response => response.json())
    .then(faculties => {
        const tableBody = document.getElementById('facultyTableBody');
        faculties.forEach(faculty => {
            const row = document.createElement('tr');
            row.innerHTML = `
              <td>${faculty.id}</td>
              <td>${faculty.name}</td>
              <td>${faculty.department}</td>
              <td>${faculty.email}</td>
              <td>
                  <button onclick="redirectToEdit('${encodeURIComponent(faculty.id)}')">Editar</button>
                  <button onclick="deleteFaculty('${encodeURIComponent(faculty.id)}')">Eliminar</button>
              </td>
          `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error('Error:', error));

function redirectToAdd() {
    window.location.href = 'add-faculty.html';
}

function redirectToEdit(facultyId) {
    window.location.href = `edit-faculty.html?id=${facultyId}`;
}

function deleteFaculty(facultyId) {
    if (confirm('¿Estás seguro de que deseas eliminar esta facultad?')) {
        fetch(`/api/faculties/${facultyId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Facultad eliminada');
                    window.location.reload();
                } else {
                    alert('Error al eliminar la facultad');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}

function redirectToMenu() {
    window.location.href = 'menu.html';
}
