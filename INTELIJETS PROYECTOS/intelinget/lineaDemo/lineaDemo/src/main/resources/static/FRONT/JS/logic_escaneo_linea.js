// Lógica para la página de escaneo de línea de producción

// Función para iniciar o reiniciar los procesos
function iniciarProcesos() {
    // Reinicia el proceso actual y la lista de componentes
    window.procesoActual = 0;
    window.listaComponentes = [];
    // Actualiza la información principal y la lista de componentes en pantalla
    if (typeof actualizarInformacion === 'function') actualizarInformacion();
    if (typeof actualizarLista === 'function') actualizarLista();
    // Reinicia los pasos del proceso
    if (typeof renderPaso === 'function') {
        pasoActual = 0;
        renderPaso();
    }
    // Limpia mensajes y campos de entrada
    const mensaje = document.getElementById('mensaje');
    if (mensaje) {
        mensaje.textContent = '';
        mensaje.className = '';
    }
    const codigoComponenteInput = document.getElementById('codigoComponente');
    if (codigoComponenteInput) {
        codigoComponenteInput.value = '';
        codigoComponenteInput.focus();
    }
    // Actualiza el botón de siguiente
    if (typeof bloquearHastaEscanear === 'function') bloquearHastaEscanear();
    if (typeof document.getElementById('btnSiguiente') !== 'undefined') {
        document.getElementById('btnSiguiente').textContent =
            (window.procesoActual === window.procesos.length - 1 && pasoActual === pasos.length - 1)
                ? "Finalizar"
                : "Siguiente";
    }
    //consultar información principal desde la BD
    // Agrega el username del usuario al request si está disponible
    const userinfo = window.userinfo || {};
    if (window.username) {
        userinfo.username = window.username;
    }
    fetch('/api/info-principal',{
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
        body: window.userinfo ? JSON.stringify(userinfo) : null,
        
        
    })
        .then(response => response.json())
        .then(data => {
            window.procesos = data.procesos || [];
            window.pasosPorProceso = data.pasosPorProceso || {};
            if (typeof actualizarInformacion === 'function') actualizarInformacion();
        })
        .catch(error => {
            console.error('Error al cargar la información principal:', error);
        });
}
// Espera a que el DOM esté completamente cargado

document.addEventListener('DOMContentLoaded', () => {
    // --- Información principal ---
    iniciarProcesos();
    // --- Escaneo de componentes ---
    window.listaComponentes = [];
    const listaUl = document.getElementById('listaComponentes');
    const mensaje = document.getElementById('mensaje');
    const agregarBtn = document.getElementById('agregarComponente');
    const codigoComponenteInput = document.getElementById('codigoComponente');
    const maxComponentes = 35;

    function actualizarLista() {
        listaUl.innerHTML = "";
        window.listaComponentes.forEach((codigo, idx) => {
            const li = document.createElement('li');
            li.textContent = codigo;
            const btn = document.createElement('button');
            btn.textContent = '⨯';
            btn.style.marginLeft = '10px';
            btn.style.background = 'none';
            btn.style.border = 'none';
            btn.style.color = '#d32f2f';
            btn.style.cursor = 'pointer';
            btn.title = 'Eliminar';
            btn.onclick = () => {
                window.listaComponentes.splice(idx, 1);
                actualizarLista();
            };
            li.appendChild(btn);
            listaUl.appendChild(li);
        });
    }



    agregarBtn.addEventListener('click', function() {
        const codigo = codigoComponenteInput.value.trim();
        if (!codigo) {
            mensaje.textContent = "Ingrese un código de componente.";
            mensaje.className = "error";
            return;
        }
        if (window.listaComponentes.includes(codigo)) {
            mensaje.textContent = "El componente ya fue escaneado.";
            mensaje.className = "error";
            return;
        }
        if (window.listaComponentes.length >= maxComponentes) {
            mensaje.textContent = "Ya se escanearon todos los componentes requeridos.";
            mensaje.className = "error";
            return;
        }
        window.listaComponentes.push(codigo);
        actualizarLista();
        mensaje.textContent = "Componente agregado correctamente.";
        mensaje.className = "success";
        codigoComponenteInput.value = "";
        codigoComponenteInput.focus();
    });

    codigoComponenteInput.addEventListener('keydown', function(e) {
        if (e.key === 'Enter') agregarBtn.click();
    });

    // --- Pasos del proceso (estático por defecto) ---
    const pasos = [
        {
            titulo: "🟦 Preparar Base Principal",
            img: "https://via.placeholder.com/180x60?text=Base+principal",
            alt: "Base principal",
            texto: "Coloque la placa base sobre la superficie de trabajo. Asegúrese de que esté correctamente orientada según la instrucción."
        },
        {
            titulo: "🔩 Insertar Tornillos",
            img: "https://via.placeholder.com/180x60?text=Tornillos+M6-20",
            alt: "Tornillos M6-20",
            texto: "Inserte los tornillos M6-20 en los orificios marcados. No apriete completamente hasta el paso final."
        },
        {
            titulo: "🟠 Colocar Arandelas",
            img: "https://via.placeholder.com/180x60?text=Colocaci%C3%B3n+de+arandelas",
            alt: "Colocación de arandelas",
            texto: "Coloque las arandelas en cada tornillo antes del ajuste final de apriete final. Verifique que estén correctamente asentadas."
        },
        {
            titulo: "✅ Proceso Completado",
            img: "https://via.placeholder.com/180x60?text=Proceso+completado",
            alt: "Proceso completado",
            texto: "¡Todos los pasos han sido completados! Puede pasar al siguiente proceso."
        }
    ];
    let pasoActual = 0;

function renderPaso() {
    const paso = pasos[pasoActual];
    document.getElementById('pasoContainer').innerHTML = `
        <div class="paso">
            <strong>${paso.titulo}</strong>
            <img src="${paso.img}" alt="${paso.alt}">
            <div>${paso.texto}</div>
        </div>
    `;
    document.getElementById('btnAnterior').disabled = pasoActual === 0;
    document.getElementById('btnSiguiente').textContent = (pasoActual === pasos.length - 1) ? "Finalizar" : "Siguiente";
}

    document.getElementById('btnAnterior').onclick = function() {
        if (pasoActual > 0) {
            pasoActual--;
            renderPaso();
        }
    };

    document.getElementById('btnSiguiente').onclick = function() {
        if (pasoActual < pasos.length - 1) {
            pasoActual++;
            renderPaso();
        } else {
            alert("Proceso finalizado. Puede pasar al siguiente proceso.");
        }
    };

    function bloquearHastaEscanear() {
        const btnSiguiente = document.getElementById('btnSiguiente');
        if (pasoActual === 0) {
            btnSiguiente.disabled = window.listaComponentes.length < maxComponentes;
        } else {
            btnSiguiente.disabled = false;
        }
    }
    setInterval(bloquearHastaEscanear, 500);
    renderPaso();


    function EscanearCodigo() {
        const codigo = codigoComponenteInput.value.trim();
        if (!codigo) {
            mensaje.textContent = "Ingrese un código de componente.";
            mensaje.className = "error";
            return;
        }
        if (window.listaComponentes.includes(codigo)) {
            mensaje.textContent = "El componente ya fue escaneado.";
            mensaje.className = "error";
            return;
        }  
        if (window.listaComponentes.length >= maxComponentes) {
            mensaje.textContent = "Ya se escanearon todos los componentes requeridos.";
            mensaje.className = "error";
            return;
        }
        fetch('/api/validar-componente', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ codigo: codigo })
        })
        .then(response => response.json())
        .then(data => {
            if (data.valido) {
                agregarComponente(codigo);
            } else {
                mensaje.textContent = "Componente inválido o no reconocido.";
                mensaje.className = "error";
            }

        })
        .catch(error => {
            console.error('Error al validar el componente:', error);
            mensaje.textContent = "Error al validar el componente. Intente nuevamente.";
            mensaje.className = "error";
        });

        function agregarComponente(codigo) {
        window.listaComponentes.push(codigo);
        actualizarLista();
        mensaje.textContent = "Componente agregado correctamente.";
        mensaje.className = "success";
        codigoComponenteInput.value = "";
        codigoComponenteInput.focus();
        bloquearHastaEscanear();
        }
    }
    document.getElementById('btnEscanear').onclick = EscanearCodigo;
    //tambien se puede escanear con Enter
    codigoComponenteInput.addEventListener('keydown', function(e) {
        if (e.key === 'Enter') EscanearCodigo();
    });
});
// Inicializa los procesos al cargar la página
iniciarProcesos();