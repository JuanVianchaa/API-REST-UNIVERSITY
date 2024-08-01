window.onload = function () {
    fetch('/api/universities/faculties')
        .then(response => response.json())
        .then(faculties => {
            const facultiesSelect = document.getElementById('faculties');
            faculties.forEach(faculty => {
                const option = document.createElement('option');
                option.value = faculty.name;
                option.textContent = faculty.name;
                facultiesSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error al cargar las facultades:', error));
};

document.getElementById('addForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const faculties = document.getElementById('faculties').value;
    const numberStudents = document.getElementById('numberStudents').value;
    const program = document.getElementById('program').value;

    fetch('/api/universities', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name, address, faculties, numberStudents, program})
    })
        .then(response => {
            if (response.ok) {
                alert('Universidad agregada');
                window.location.href = 'universities.html';
            } else {
                alert('Error al agregar la universidad');
            }
        })
        .catch(error => console.error('Error:', error));
});

function redirectToUniversities() {
    window.location.href = 'universities.html';
}
