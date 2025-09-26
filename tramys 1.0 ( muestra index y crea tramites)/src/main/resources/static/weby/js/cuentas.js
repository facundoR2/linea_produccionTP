Vue.createApp({
    data() {
        return {
            clienteInfo: {},
            errorToats: null,
            errorMsg: null,
           
        }
    },
    mounted(){
        this.getData();
    },
    methods: {
        getData: function () {
            axios.get("/api/usuario/current")
                .then((response) => {
                    this.clienteInfo = response.data;
                    console.log(this.clienteInfo);
                })
                .catch((error) => {
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })
        },
        formatDate: function (date) {
            return new Date(date).toLocaleDateString('en-gb');
        },
        abrirModalConfirmacion(){
            const modalElement = document.getElementById('confirmacionModal');
            const bootstrapModal = new bootstrap.Modal(modalElement);
            bootstrapModal.show();
        },
        signOut: function () {
            axios.post('/api/logout')
                .then(response =>
                    alert("SE ha cerrado sesion correctamente"), 
                    window.location.href = "/weby/index.html")
                .catch(() => {
                    this.errorMsg = "Error al intentar cerrar Sesion."
                    this.errorToats.show();
                })
         },
        async confirmarBaja(){
            if(this.clienteInfo){  //si existe un clienteInfo
                try{
                    const response = await axios.put(`/api/usuario/baja`, this.clienteInfo);


                    if(response.status === 200){
                        alert('tu cuenta ha sido dada de baja');
                        this.signOut();

                        
                    }else{
                        alert('Error al dar de baja tu cuenta');
                    }

                }catch (error){
                    console.error('error en la solicitud',error);
                    alert('Hubo un problema al tratar de bajar tu cuenta');

                }finally {
                    const modalElement = document.getElementById('confirmacionModal');
                    const bootstrapModal = bootstrap.Modal.getInstance(modalElement);
                    bootstrapModal.hide();
                }
            }
        }
       
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.modal = new bootstrap.Modal(document.getElementById('confirModal'));
        this.getData();
    }
}).mount('#app')