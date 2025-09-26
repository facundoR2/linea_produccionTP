const app = Vue.createApp({
    data() {
        return {
            nombre: "",
            apellido: "",
            correo1: "",
            correo2: "",
            password: "",
            mostratPassword: false
        };
    },
    mounted: function () {

    },
    methods: {
        togglePassword: function () {
            this.mostratPassword = !this.mostratPassword;
        },
        toast(){
            

            function ok_toast(texto){
                var toastHTMLElement = document.getElementById("ok-toast");
                var toastElement = new bootstrap.Toast(toastHTMLElement);
                toastHTMLElement.querySelector('.toast-body').textContent = texto;
                toastElement.show();

            }
            function error_toast(texto){
                var toastHTMLElement = document.getElementById("error-toast");
                var toastElement = new bootstrap.Toast(toastHTMLElement);
                toastHTMLElement.querySelector('.toast-body').textContent = texto;
                toastElement.show();
            }

            return{ok_toast, error_toast};


        },
        ValidarCampos: function(){
            const tosty = this.toast();
            console.log(this.nombre, this.apellido, this.correo1, this.correo2, this.password
            );

            if( !this.nombre || !this.apellido || !this.correo1 || !this.correo2 || !this.password){
                this.errorMsg = "Por favor complete todos los campos";
                console.log("Por favor complete todos los campos");
                tosty.error_toast("Por favor complete todos los campos");
                return false;  
            }
            return true;
            
        },
        ValidarCorreo: function(){
            const tosty = this.toast();

            //Validar que el correo sea correcto
            const dominiosPermitidos = ["gmail.com", "hotmail.com", "yahoo.com", "outlook.com"];
            if(dominiosPermitidos.includes(this.correo2)) {
                console.log= "Dominio de correo permitido";
                tosty.ok_toast("Dominio de correo permitido");
            }else if(this.correo1.length <3 || this.correo1.length > 30){
                //En caso de error mostrar por consola, y en la pantalla
                console.log("Nombre de usuario inválido - Longitud incorrecta");
                this.errorMsg = "Nombre de usuario inválido - Longitud incorrecta";
                tosty.error_toast("Nombre de usuario inválido - Longitud incorrecta");
                return false;
            }
    
            return true;
            


        },
        irAIndex: function (){
            window.location.href = "http://localhost:8080/weby/index.html";
        },
        register(event) {
            event.preventDefault(); // evita el envio automatico del formulario.

            if(this.ValidarCampos() && this.ValidarCorreo()){
                this.registrarUsuario();
                
            }
        },
        async registrarUsuario(){
            let config = {
                headers: {
                    'content-type': 'application/json'
                }
            }
            try {
                const toasty = this.toast();
                const usuarioDTO = {
                    nombre: this.nombre,
                    apellido: this.apellido,
                    correo: this.correo1 + "@" + this.correo2,
                    password: this.password
                
                };
                const response = await axios.post('/api/usuario', JSON.stringify(usuarioDTO), config);
                
                if(response.data == "Usuario ya registrado"){
                    this.errorMsg = "Usuario ya registrado";
                    toasty.error_toast("Usuario ya registrado");
                }else if(response.data == "USUARIO creado correctamente"){
                    toasty.ok_toast("Usuario registrado correctamente");
                    this.irAIndex();
                }else if(response.status === 404){
                    console.log("Error 404, no hay conexion con el Servidor");
                    this.errorMsg = "Error 404, no hay conexion con el Servidor";
                    toasty.error_toast("Error 404, no hay conexion con el Servidor");
                }
                
            } catch (error) {
                console.error("Error en la solicitud", error);
                this.errorMsg = "Error en la solicitud, posibles problemas con el servidor";
                const toasty = this.toast();
                toasty.error_toast("Error en la solicitud, posibles problemas con el servidor");
                
            }
            //genero un cliente
            
        },
            
    },
    
});
app.mount('#app');