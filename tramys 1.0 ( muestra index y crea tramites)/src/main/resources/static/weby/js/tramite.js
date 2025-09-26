const app = Vue.createApp({
    data() {
        return {
            nombreTramite: '',
        };
    },
    mounted(){ //lo que realiza Vue una vez montada.
        this.buscarTramiteRecibido();
        
        
        
        
    },
    
    
    methods:{
        fetchTramiteEncontrado(nombreTramite){ //en caso de que se acceda a la pagina tramite a traves de otra pagina.(index, recinto,etc)
           
            //realiza la peticion get al controlador.
            axios.get('/api/tramite/buscarTramite', {
                params: {nombreTramite: nombreTramite}
            })
            .then(response => {
                console.log(response.data);
                const tramites = response.data;
                const contenidoDiv = document.getElementById('contenido');
                const tramiteDiv = document.createElement('div');
                tramiteDiv.innerHTML = `
                    <h3>${tramites.nombre}</h3>
                    <p>Recinto: ${tramites.nombreRecinto}</p>
                    <ul>
                    ${tramites.pasos.map(paso => `
                        <li>
                            paso: ${paso.nombre}
                            <ul>
                                <li>descripcion: ${paso.descripcion}</li>
                            </ul>
                        </li>
                    `).join('')}                   
                    </ul>
                    `;
                    contenidoDiv.appendChild(tramiteDiv);
                
                

                
            })

        },
        buscarTramiteRecibido(){
            try {
                var nombreTramite = localStorage.getItem("nombreTramite");
                if(nombreTramite == null){
                    console.log("No se encontro el tramite");
                    throw new Error("No se encontro el tramite");
                }
                else{
                    console.log("Tramite encontrado");
                    this.fetchTramiteEncontrado(nombreTramite);
                }
             } catch (error) {
                    console.log("Error al buscar el tramite", error);

                
            }
            

        },
        irACuentas: function(){
            window.location.href = "http://localhost:8080/weby/cuentas.html";
            
        },
        irAIndex: function(){
            window.location.href = 'http://localhost:8080/weby/index.html';
        },

    },
    
    


});
app.mount('#app');