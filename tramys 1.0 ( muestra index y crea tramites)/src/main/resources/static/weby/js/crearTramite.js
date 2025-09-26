const app = Vue.createApp({
    data() {
        return {
            nombreTramite: '',
            busquedaRecinto: '',
            ResultadoRecinto: [],
            nombreRecinto: null,
            pasos: []
        };
        
    },
    methods: {
        async buscarRecinto(){
            try{
                const response = await axios.get('/api/recinto/buscar',{
                    params: {nombre: this.busquedaRecinto}
                });
                this.ResultadoRecinto = response.data;
            }catch(errorBusquedaRecinto){
                console.error('Error al buscar recinto:',errorBusquedaRecinto);
            }
        },

        seleccionarRecinto(recinto){
            this.nombreRecinto = recinto;
            this.ResultadoRecinto = []; //se limpia los resultados de la busqueda.
            this.busquedaRecinto = recinto.nombre; //mostramos el recinto seleccionado.
            this.addStep();
        },
        addStep() {
            this.pasos.push({
                nombre: '',
                descripcion: '',});
        },
        async submitTramite() {
            if(!this.nombreTramite || !this.nombreRecinto || this.pasos.length === 0){
                alert('Por favor complete todos los campos y agrege al menos un paso');
                return;
            }
            try{
                const TramiteDTO = {
                    pasos: this.pasos,
                    nombre: this.nombreTramite,
                    nombreRecinto: this.nombreRecinto.nombre
                
                };
                 //const json = JSON.parse(JSON.stringify(tramiteDTO));

    
                console.log('Formulario de tramite enviado:',TramiteDTO);


    
                const response = await axios.post('/api/tramite/crear',TramiteDTO);

                if(response.status == 200){
                    console.log('Tramite creado exitamente',response.data);
                    alert('tramite creado con exito');
                    this.resetForm();

                }
                
                    
                
                
    
    
            }catch(error){
                console.error('Error al crear tramite: ',error);
                alert('HUBO Un error al crear tramite, por favor intentelo denuevo');
            };
            
        },
        resetForm(){
            this.nombreTramite = '';
            this.busquedaRecinto = '';
            this.ResultadoRecinto = [];
            this.nombreRecinto = null;
            this.pasos = [{nombre: '', descripcion: ''}];
        },
        irACuentas(){
            window.location.href='cuentas.html';
        }
            
    }
    
});
app.mount('#app');