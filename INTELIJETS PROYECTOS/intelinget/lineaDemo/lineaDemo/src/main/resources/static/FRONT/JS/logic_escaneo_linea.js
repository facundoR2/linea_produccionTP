// Lógica para la página de escaneo de línea de producción

const { use } = require("react");

//generamos Un ValeLectura para trackear el progreso de cada Producto.
const Lectura = {
    codigoproducto: "", //codigo serial del producto terminado.
    puestoActual: 0,
    tiempo: Date.now(),
    estado: "En Proceso", // "En Proceso", "Completado"
    componentes: [{
        nombre: "",
        codigo: "",  //codigo del producto(componente)
        cantidad: 0
    }],
    usuario: "",

}


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
}
function crearVale() {

    //traemos el usuario logueado
    const UsuarioDTO = {
        id: window.userId || 0,
        username: window.username || "invitado",
        nombre: window.nombre || "Invitado",
        
    };


    //consultamos el ENDPOINT y traemos el vale.
    fetch('http://localhost:9090/api/produccion/crear-Vale/${UsuarioDTO}',{
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(UsuarioDTO)
    }).then(response => {
        if (!response.ok) {
            throw new Error('Error en la respuesta del servidor');
        }
        return response.json();
    })
    .then(data => {
        //guardamos el vale en una variable global
        window.Lectura = data || {};
        console.log('Vale creado:', window.Lectura);
    })
    .catch(error => {
        console.error('Error al crear el vale:', error);
        const mensaje = document.getElementById('mensaje');
        if (mensaje) {
            mensaje.textContent = "Error al crear el vale. Intente nuevamente.";
            mensaje.className = "error";
        }
    });
    actualizarVale();
}
function actualizarVale(){
    window.Lectura.codigoproducto = document.getElementById('codigoProducto').textContent || "";

}
    








// Espera a que el DOM esté completamente cargado
function buscarInfo() {
    fetch('http://localhost:9090/api/produccion/info-general')
    .then(response => response.json())
    .then(data => {
        // Actualiza la información en la página
        document.getElementById('codigoProducto').textContent = data.codigoProducto || 'N/A';
        document.getElementById('ordenCarga').textContent = data.ordeProduccion || 'N/A';
        document.getElementById('cantidadProduccion').textContent = data.cantidadProduccion || 'N/A';
        document.getElementById('procesoActual').textContent = data.procesoActual || 'N/A';
        window.procesos = data.procesos || [];
        if (window.procesos.length > 0) {
            document.getElementById('descripcionProceso').textContent = window.procesos[window.procesoActual].descripcion || 'N/A';
        }
    });
   
}
function comprobarProceso() {
        // Envía el JSON con la información actual a la BD y obtiene la cantidad de componentes por proceso
        const payload = {
            procesoActual: window.procesoActual,
            componentesEscaneados: window.listaComponentes,
            usuario: window.username || ""
        };

        fetch('http://localhost:9090/api/produccion/comprobar-proceso', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        })
        .then(response => response.json())
        .then(data => {
            // Actualiza la cantidad máxima de componentes requeridos para el proceso actual
            window.maxComponentes = data.cantidadComponentes || 35;
            // Actualiza la UI si es necesario
            if (typeof actualizarLista === 'function') actualizarLista();
            if (typeof bloquearHastaEscanear === 'function') bloquearHastaEscanear();
        })
        .catch(error => {
            console.error('Error al comprobar el proceso:', error);
            const mensaje = document.getElementById('mensaje');
            if (mensaje) {
                mensaje.textContent = "Error al consultar la cantidad de componentes. Intente nuevamente.";
                mensaje.className = "error";
            }
        });
    }
    function pasarAlsuiguientePuesto(Lectura) {
        const confirmacion = window.confirm("¿Desea pasar al siguiente puesto?");
        if (!confirmacion) return;
        if (confirmacion) {
            // Envía el JSON con la información actual a la BD para pasar al siguiente puesto
            fetch('http://localhost:9090/api/produccion/valeLectura/${lecturaId}/siguiente'),{
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(Lectura)
            }
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor');
                }
                return response.json();
            })
            .then(data => {
                // informar al usuario que se pasó al siguiente puesto
                alert("Producto enviado al siguiente puesto");
                console.log('Respuesta del servidor:', data);
            })
            .catch(error => {
                console.error('Error al pasar al siguiente puesto:', error);
                const mensaje = document.getElementById('mensaje');
                if (mensaje) {
                    mensaje.textContent = "Error al pasar al siguiente puesto. Intente nuevamente.";
                    mensaje.className = "error";
                }
            });
        }else {
            alert("Operación cancelada. El producto permanece en el puesto actual.");
        }
        
    }



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