function editCart(cartItemId, buyCount) {
    window.location.href = 'cart.do?operate=editCart&cartItemId=' + cartItemId + '&buyCount=' + buyCount;
}

window.onload = function () {
    var vue = new Vue({
        el: "#cart_div",
        data: {
            cart: ""
        },
        methods: {
            getCart: function () {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: 'cartInfo'
                    }
                })
                    .then(function (value) {
                        vue.cart = value.data;
                    })
                    .catch(function (reason) {

                    });
            },
            editCart: function (cartItemId, buyCount) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: 'editCartCount',
                        cartItemId: cartItemId,
                        buyCount: buyCount
                    }
                })
                    .then(function (value) {
                        console.log('code = ' + value.data.code);
                        vue.getCart();
                    })
                    .catch(function (reason) {

                    });
            }
        },
        beforeMount: function () {
            this.getCart();
        }
    });
}