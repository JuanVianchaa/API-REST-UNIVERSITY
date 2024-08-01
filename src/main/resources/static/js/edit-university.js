const urlParams = new URLSearchParams(window.location.search);
const universityId = urlParams.get('id');

fetch(`/api/universities/${universityId}`)
    .then(response => {
        if (!response.ok) throw new Error('Error al cargar la universidad');
        return response.json();
    })
    .then(university => {
        document.getElementById('name').value = university.name;
        document.getElementById('address').value = university.address;
        document.getElementById('numberStudents').value = university.numberStudents;
        document.getElementById('program').value = university.program;

        const facultiesSelect = document.getElementById('faculties');
        const option = document.createElement('option');
        option.value = university.faculties;
        option.textContent = university.faculties;
        facultiesSelect.appendChild(option);
    })
    .catch(error => console.error('Error:', error));


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

document.getElementById('editForm').addEventListener('submit', function (event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const address = document.getElementById('address').value;
    const faculties = document.getElementById('faculties').value;
    const numberStudents = document.getElementById('numberStudents').value;
    const program = document.getElementById('program').value;

    fetch(`/api/universities/${universityId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({name, address, faculties, numberStudents, program})
    })
        .then(response => {
            if (response.ok) {
                alert('Universidad actualizada');
                window.location.href = 'universities.html';
            } else {
                alert('Error al actualizar la universidad');
            }
        })
        .catch(error => console.error('Error:', error));
});

function redirectToUniversities() {
    window.location.href = 'universities.html';
}
