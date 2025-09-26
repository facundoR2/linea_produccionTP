Vue.createApp({
    data() {
        return {
            correo: "",
            password: "",
            cliente: {}
            
        }
    },
    methods: {
        register: function (event) {
            event.preventDefault();
            let config = {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            }
            axios.post('/api/usuario', `correo=${this.correo}&password=${this.password}`, config)
                .then(response => window.location.href = "/web/cuentas.html")
                .catch(() => {
                    this.errorMsg = "Sign in failed, check the information"
                    this.errorToats.show();
                })
        },
        mostrarCliente: function(){
            console.log(this.cliente);
        }      
    },
    mounted: function () {

    }
}).mount('#app')