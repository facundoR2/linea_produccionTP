Vue.createApp({
    data() {
        return {
            usuarioInfo: {},
            errorToats: null,
            errorMsg: null,
        }
    },
    methods: {
        getData: function () {
            const urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            axios.get(`/api/cuentas/${id}`)
                .then((response) => {
                    this.usuarioInfo = response.data;
                    this.usuarioInfo.transacciones.sort((a, b) => parseInt(b.id - a.id))
                })
                .catch((error) => {
                    this.errorMsg = "Error getting data";
                    this.errorToats.show();
                })
        },
        formatDate: function (date) {
            return new Date(date).toLocaleDateString('en-gb');
        },
        baja: function () {
            urlParams = new URLSearchParams(window.location.search);
            const id = urlParams.get('id');
            axios.get(`/api/cuentas/${id}`)
            //consigo el usuario y luego lo coloco dentro de usuarioInfo.
            .then((response) => {
                this.usuarioInfo = response.data;
                //lo mando como peticision put a la direccion con el usuario como body.
                fetch('/api/usuario/baja',{
                    method:'PUT',
                    headers:{
                        'Content-type':'application/json'
                    },
                    body: JSON.stringify(usuarioInfo)

                })
                //respuesta de la peticion.
                .then(response => response.json())
                .then(data =>{
                    console.log('Logrado',data);
                    alert('Usuario enviado para baja');
                })
                .catch((error) =>{
                    console.log('Error:',error);
                    alert("Error al enviar datos");
                });

            });

        },
        signOut: function () {
            axios.post('/api/logout')
                .then(response => window.location.href = "/web/index.html")
                .catch(() => {
                    this.errorMsg = "Sign out failed"
                    this.errorToats.show();
                })
        },
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.getData();
    }
}).mount('#app')