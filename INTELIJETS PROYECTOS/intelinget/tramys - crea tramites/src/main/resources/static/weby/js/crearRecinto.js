const app = Vue.createApp({
    data(){
        return{
            recintoExiste: '',
            //agregarTramite: 'no',
            direccion: '',
            nombre: '',
            horarioApertura: '',
            horarioCierre: '',
            map: null,
            marcador: null,
            latitud: null,
            longitud: null
        };
    },
    watch: {
        //updateMap(direccion){},
        recintoExiste(value) {
            this.toggleMap(value);
        }
    },
    mounted(){
        this.toggleMap(this.recintoExiste);
        
        const direccionInput = document.getElementById('direccion-input');
        if(direccionInput) {
            direccionInput.addEventListener('keydown',(Event) => {
                if(Event.key === 'Enter') {
                    Event.preventDefault();
                    this.updateMap(this.direccion);
                }
            });
        }
    },
    methods: {
        toggleMap(value) {
            const mapElement = document.getElementById('map');
            if(!mapElement) return; // salir si el elemento no esta disponible aun.
            if(value === 'no'){
                mapElement.style.display = 'block'; // muestra el mapa.
                if(!this.map) {
                    this.initializeMap(); //si no esta inicializado, lo inicializa al mapa.
                }
            } else {
                mapElement.style.display = 'none'; // oculta el mapa
                if(this.map) {
                    this.map.remove(); //si el mapa esta inicializado, lo borra.
                    this.map = null;
                }
            }

        },
        initializeMap(){
            this.map = L.map('map').setView([-53.7857, -67.7016], 13);

            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.map);


            
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

                    //if(!resultadoValido){
                     //   resultadoValido = data.find(item => item.class === "amenity");
                        //resultadoValido.lat = data.find(item => item.lat);
                        //resultadoValido.lon = data.find(item => item.lon);
                    //}



                    if(data.length > 0 && resultadoValido) {
                        this.latitud = resultadoValido.lat;
                        this.longitud = resultadoValido.lon;

                        this.map.setView([this.latitud, this.longitud], 10);

                        //removemos marcador anterior.
                        if(this.marker){
                            this.map.removeLayer(this.marker);
                        }
                        this.marcador = L.marker([this.latitud, this.longitud], {draggable: true}).addTo(this.map);
                        this.map.setView([this.latitud, this.longitud], this.map.getZoom());

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
        submitForm(){
            const crearRecintoDTO ={
                lat: this.latitud,
                lng: this.longitud,
                nombre: this.nombre,
                direccion: this.direccion,
                hAtencion: this.horarioApertura,
                hCierre: this.horarioCierre,
                
            };
            //conmprobacion de recinto
            console.log('recinto enviado:',crearRecintoDTO);

            axios.post('/api/recinto/nuevoRecinto', crearRecintoDTO)
            .then(response => {
                console.log('Recinto creado exitosamente',response.data);
                alert('Recinto creado exitosamente');
                window.location.href = 'cuentas.html';
            })
            .catch(error =>{
                console.error('Error al crear el recinto: ', error);
                alert('Hubo un error al crear el recinto. Por favor, intenta de nuevo');
            });
        }
    }
   
});
app.mount('#app');