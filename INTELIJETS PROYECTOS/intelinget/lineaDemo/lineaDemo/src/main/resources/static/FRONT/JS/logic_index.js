//campturar los elementos del DOM
const password = document.getElementById('password');
const user = document.getElementById('username');
const loginButton = document.getElementById('loginButton');


// Agregar un event listener al botón de inicio de sesión
loginButton.addEventListener('click', function(event) {
    event.preventDefault(); // Evitar el envío del formulario por defecto
    validarCredenciales();
});

// Función para validar las credenciales
function validarCredenciales() {
    const usuario = user.value;
    const contrasena = password.value;
    fetch('http://localhost:9090/api/usuarios/validar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username: usuario, password: contrasena })
    })
    .then(response => response.json())
    .then(data => {
        if (data.valid) {
            // Credenciales válidas, redirigir a la página principal
            window.location.href = 'index.html';
        } else {
            // Credenciales inválidas, mostrar mensaje de error
            alert('Credenciales inválidas. Por favor, inténtalo de nuevo.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Ocurrió un error al validar las credenciales. Por favor, inténtalo de nuevo más tarde.');
    });
}