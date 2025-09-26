// //codigo para generar el mapa
// var map = L.map('map').setView([-53.7857,-67.7016], 13);

// //se agrega capa de mapa de openstreetmap
// L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',{
//     maxZoom: 19,
// }).addTo(map);

// //evento para capturar el click en el mapa
// map.on('click',function(e) {
//     var lat = e.latlng.lat;
//     var lng = e.latlng.lng;

//     //muestra un marcador en posicion seleccionada
//     L.marker([lat,lng]).addTo(map)
//     .bindPopup("Latitud: "+ lat + "<br>Longitud: " + lng)
//     .openPopup();

//     saveCoordinates(lat,lng);
// });




const app = Vue.createApp({
    data() {
        return {
            recintos: [], //lista para los recintos
            direccion: '', //direccion para buscar en el mapita
            latitud: null, //latitud obtenida
            longitud: null, //longitud obtenida
            mapa: null,
            marcador: null, //marcador de la ubicacion buscada.
            cliente: null,
            errorMsg: ''
        };
    },
    
    
    methods:{
        initMap(){
            this.mapa = L.map('map').setView([-53.7857, -67.7016], 13);
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',{
                maxZoom:19,
                attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            }).addTo(this.mapa);
        },
        fetchRecintos(){
            axios.get('/api/recinto/traerTanda')
            .then(response => {
                console.log(response.data);
                this.recintos = response.data; //guardar recintos
                this.addMarkers();
            })
            .catch(error =>{
                console.error("error al obtener recintos:", error);
            });

        },
        addMarkers(){
            //por cada recinto, crear marcador
            this.recintos.forEach(recinto => { //para cada recinto, crea un marcador para poner en el mapa.
                const marker = L.marker([recinto.lat, recinto.lng]).addTo(this.mapa);
                marker.bindPopup(`
                    <b>${recinto.nombre}</b><br>
                    ${recinto.direccion}`); 
            });
            const group = L.featureGroup(this.recintos.map(recinto => L.marker([recinto.lat, recinto.lng])));
            this.map.fitBounds(group.getBounds());
        },
        async buscarDireccion(){
            try{
                const response = await fetch(`https://nominatim.openstreetmap.org/search?format=json&q=${encodeURIComponent(this.direccion)}`);
                const data = await response.json();

                //filtrar para los resultado tipo "calle"
                
                const resultadoValido = data.find(item => item.osm_type === "way" && item.class ==="place");

                // if(!resultadoValido){
                //     resultadoValido = data.find(item => item.class === "amenity");
                // }

                //verificacion de si se obtuvo resultados
                if (data.length > 0 && resultadoValido) {
                    //guardar latitud y longitud del resultado
                    this.latitud = parseFloat(resultadoValido.lat);
                    this.longitud = parseFloat(resultadoValido.lon);

                    //centrar mapa  sobre   la direccion buscada.
                    this.mapa.setView([this.latitud, this.longitud], 13);

                    //removemos el marcador anterior
                    if(this.marcador){
                        this.mapa.removeLayer(this.marcador);
                        
                        
                    }
                    this.marcador = L.marker([this.latitud, this.longitud]).addTo(this.mapa);
                    this.mapa.setView([this.latitud, this.longitud], 13);

                    //mostrar popup con la direccion.
                    
                    this.marcador.bindPopup(`Direccion: ${this.direccion}`).openPopup();



                }else{
                    alert('No se encontró la direccion proporcionada.');
                }
            }catch(error) {
                console.error('Error al buscar direccion:',error);
            }

        },
        irACuentas: function(){
            window.location.href = 'cuentas.html';
        }
    },
    mounted(){
        this.initMap(); // funcion para crear mapa
        this.fetchRecintos(); //funcion 
        
        
    }
    
    
});
app.mount('#app');

