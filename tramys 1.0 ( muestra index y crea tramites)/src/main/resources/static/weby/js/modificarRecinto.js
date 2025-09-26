const app = Vue.createApp({
    data() {
        return {
            busquedaRecinto: '',
            ResultadoRecinto: [],
            recintoSeleccionado: null,
            nombre: '',
            direccion: '',
            horarioApertura: '',
            horarioCierre: '',
            map: null,
            marcador: null,
            latitud: null,
            longitud: null,
            
            
            ModRecintoDTO: {
                lat: '',
                lng: '',
                nombreAnteriorRecinto:'',
                nombreNuevoRecinto: '',
                direccion: '',
                hAtencion: '',
                hCierre: '',

            } // objeto para guardar el recinto modificado.
           
        };
        
    },
    mounted(){
        this.initializeMap();
        
        
        
       
    },
    methods: {
        initializeMap(){
            const mapcontenedor = document.getElementById('map');
            console.log('Contenedor de mapa:',mapcontenedor);
            if(this.map) return; // si ya existe, no lo crea.

            if(!document.getElementById('map')){
                console.error("no se encuentra el contenedor de mapa")
            }
            
            this.map = L.map('map').setView([this.latitud || -53.7857, this.longitud || -67.7016], 13);

            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map);


            
        },
        async buscarRecinto(){
            try{
                const response = await axios.get('/api/recinto/buscar',{
                    params: {nombre: this.busquedaRecinto }
                });
                this.ResultadoRecinto = response.data;
            }catch(errorBusquedaRecinto){
                console.error('Error al buscar recinto:',errorBusquedaRecinto);
            }
        },

        seleccionarRecinto(recinto){
            this.ResultadoRecinto = []; //se limpia los resultados de la busqueda.
            this.recintoSeleccionado = recinto;
            this.nombre = recinto.nombre; // mostramos el nombre del recinto
            this.direccion = recinto.direccion;
            this.horarioApertura = recinto.hAtencion; //mostramos el horario del recinto
            this.horarioCierre = recinto.hCierre; //mostramos el horario de cierre del recinto

            this.latitud = recinto.lat;
            this.longitud = recinto.lng;
            this.updateMap(this.direccion);

            this.ModRecintoDTO.nombreAnteriorRecinto = recinto.nombre; // guardo el nombre del recinto para encontrarlo luego.
        },
        
        async updateMap(direccion) {
            if(this.map && direccion.trim() !== '') {
                if(this.marcador) {
                    this.map.removeLayer(this.marcador); // borra marcador anterior si existe
                }
                try{
                    const response = await fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(direccion)}`);
                    const data = await response.json();

                    //filtrar entre los tipo calle.
                    let resultadoValido = data.find(item =>item.osm_type === "way" && item.class ==="place");



                    if(data.length > 0 && resultadoValido) {
                        this.latitud = resultadoValido.lat;
                        this.longitud = resultadoValido.lon;

                        this.map.setView([this.latitud, this.longitud], 13);

                        //removemos marcador anterior.
                        if(this.marcador){
                            this.map.removeLayer(this.marcador);
                        }
                        this.marcador = L.marker([this.latitud, this.longitud], {draggable: true}).addTo(this.map);
                        this.map.setView([this.latitud, this.longitud], 13);

                        this.marcador.on('dragend', () =>{
                            const position = this.marcador.getLatLng();
                            this.latitud = position.lat;
                            this.longitud = position.lng;
                        })

                        //mostrar popup con la direccion
                        this.marcador.bindPopup(`Direccion: ${this.direccion}`).openPopup();

                    }else{
                        alert('No se encontró la direccion proporcionada');
                    }
                }catch(error) {
                    console.error('Error al buscar la direccion:',error);
                } 
            }
        },
      
        async submitRecintoModificado() {
            if(!this.nombre || !this.direccion || !this.horarioApertura || !this.horarioCierre ){
                alert('Por favor complete todos los campos para modificar el recinto');
                return;
            }
            try{
                this.ModRecintoDTO.lat = this.latitud;
                this.ModRecintoDTO.lng = this.longitud;
                this.ModRecintoDTO.nombreNuevoRecinto = this.nombre;
                this.ModRecintoDTO.direccion = this.direccion;
                this.ModRecintoDTO.hAtencion = this.horarioApertura;
                this.ModRecintoDTO.hCierre = this.horarioCierre;
                  
                  
                 //const json = JSON.parse(JSON.stringify(tramiteDTO));

    
                console.log('Formulario de tramite enviado:',this.ModRecintoDTO);


    
                const response = await axios.put('/api/recinto/actualizar',this.ModRecintoDTO);

                if(response.status == 200){
                    console.log('Recinto modificado exitamente',response.data);
                    alert('Recinto modificado creado con exito');
                    this.resetForm();

                }
                
                    
                
                
    
    
            }catch(error){
                console.error('Error al modificar el recinto: ',error);
                alert('HUBO Un error al modificar el recinto, por favor intentelo denuevo');
            };
            
        },
        resetForm(){
            this.busquedaRecinto = '';
            this.ResultadoRecinto = [];
            this.nombreRecinto = null;
            this.nombre = '';
            this.direccion = '';
            this.horarioApertura = '';
            this.horarioCierre = '';
            this.ModRecintoDTO = {
                lat:'',
                lng:'',
                nombreAnteriorRecinto: '',
                nombreNuevoRecinto: '',
                direccion:'',
                horarioApertura:'',
                horarioCierre:'',
            };  
            
        }
            
    }
    
});
app.mount('#app');