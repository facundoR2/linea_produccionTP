const ordenes = [];
        document.getElementById('crearOrdenBtn').onclick = function() {
            const orden = document.getElementById('nuevaOrden').value.trim();
            const producto = document.getElementById('productoPadre').value.trim();
            const linea = document.getElementById('lineaAsignada').value;
            if (!orden || !producto || !linea) return alert('Complete todos los campos');
            ordenes.push({orden, producto, linea, estado: 'Pendiente'});
            actualizarTabla();
            document.getElementById('nuevaOrden').value = '';
            document.getElementById('productoPadre').value = '';
            document.getElementById('lineaAsignada').value = '';
        };
        function actualizarTabla() {
            const tbody = document.getElementById('tablaOrdenes').querySelector('tbody');
            tbody.innerHTML = '';
            ordenes.forEach(o => {
                const tr = document.createElement('tr');
                tr.innerHTML = `<td>${o.orden}</td><td>${o.producto}</td><td>${o.linea}</td><td>${o.estado}</td>`;
                tbody.appendChild(tr);
            });
        }
        document.getElementById('generarReporteBtn').onclick = function() {
            alert('Funcionalidad de reporte no implementada en demo.');
        };
        document.getElementById('consultarStockBtn').onclick = function() {
            const q = document.getElementById('consultaStock').value.trim().toUpperCase();
            let msg = '';
            if (q === 'PB-7845-XR') msg = 'Stock: 34 unidades';
            else if (q === 'TORNILLO M6-20') msg = 'Stock: 1.200 unidades';
            else if (q === 'CP-3421-AB') msg = 'Stock: 8 unidades';
            else if (q) msg = 'Elemento no encontrado o sin stock.';
            else msg = '';
            document.getElementById('resultadoStock').textContent = msg;
        };