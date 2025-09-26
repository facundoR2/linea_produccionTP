Vue.createApp({
    data() {
        return {
            correo: "",
            password: "",
            cliente: {}
            
        }
    },
    methods: {
        irARegistrarse: function(){
            window.location.href ='registrarse.html'

        },
        signIn: function (event) {
            event.preventDefault();
            let config = {
                headers: {
                    'content-type': 'application/x-www-form-urlencoded'
                }
            }
            axios.post('/api/login', `correo=${this.correo}&password=${this.password}`, config)
                .then(response => window.location.href = "/weby/cuentas.html")
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