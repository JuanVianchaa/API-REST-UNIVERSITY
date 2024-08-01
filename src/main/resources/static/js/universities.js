fetch('/api/universities')
    .then(response => response.json())
    .then(universities => {
        const tableBody = document.getElementById('universityTableBody');
        universities.forEach(university => {
            const row = document.createElement('tr');
            row.innerHTML = `
              <td>${university.id}</td>
              <td>${university.name}</td>
              <td>${university.address}</td>
              <td>${university.faculties}</td>
              <td>${university.numberStudents}</td>
              <td>${university.program}</td>
              <td>
                  <button onclick="redirectToEdit('${university.id}')">Editar</button>
                  <button onclick="deleteUniversity('${university.id}')">Eliminar</button>
              </td>
          `;
            tableBody.appendChild(row);
        });
    })
    .catch(error => console.error('Error:', error));

function redirectToAdd() {
    window.location.href = 'add-university.html';
}

function redirectToEdit(universityId) {
    window.location.href = `edit-university.html?id=${universityId}`;
}

function deleteUniversity(universityId) {
    if (confirm('¿Estás seguro de que deseas eliminar esta universidad?')) {
        fetch(`/api/universities/${universityId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    alert('Universidad eliminada');
                    window.location.reload();
                } else {
                    alert('Error al eliminar la universidad');
                }
            })
            .catch(error => console.error('Error:', error));
    }
}

function redirectToMenu() {
    window.location.href = 'menu.html';
}
