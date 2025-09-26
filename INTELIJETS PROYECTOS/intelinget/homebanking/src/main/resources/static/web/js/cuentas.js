Vue.createApp({
    data() {
        return {
            clienteInfo: {},
            errorToats: null,
            errorMsg: null,
            accountToDeleteId: null,
        }
    },
    methods: {
        getData: function () {
            //Obtendrá solo un cliente seleccionado por ahora, en mi caso Rejas tiene id 7
            axios.get("/api/clientes/current")
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
        // signOut: function () {
        //     axios.post('/api/logout')
        //         .then(response => window.location.href = "/web/index.html")
        //         .catch(() => {
        //             this.errorMsg = "Sign out failed"
        //             this.errorToats.show();
        //         })
        // },
        // create: function () {
        //     axios.post('/api/clients/current/accounts')
        //         .then(response => window.location.reload())
        //         .catch((error) => {
        //             this.errorMsg = error.response.data;
        //             this.errorToats.show();
        //         })
        // },
        deleteAccount: function (accountId) {
            this.accountToDeleteId = accountId;
            this.modal.show();
        },        
        confirmDelete: function (accountId) {
            console.log("Confirm Delete - Account ID: ", accountId);
    
            axios.delete(`/api/clients/current/accounts/${accountId}`)
                .then(response => {
                    console.log("Account deleted successfully.");
                    this.modal.hide();
                    window.location.href = "/web/accounts.html";
                })
                .catch((error) => {
                    console.log(error);
                    this.errorMsg = error.response.data;
                    this.errorToats.show();
                });
        }
    },
    mounted: function () {
        this.errorToats = new bootstrap.Toast(document.getElementById('danger-toast'));
        this.modal = new bootstrap.Modal(document.getElementById('confirModal'));
        this.getData();
    }
}).mount('#app')