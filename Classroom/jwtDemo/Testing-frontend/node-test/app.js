const app = {
    greet: function(name) {
        return `Hello, ${name}!`;
    },

    greetInHindi: function(name) {
        return `नमस्ते, ${name}!`;
    },

    processOrder: function(data, dependency) {
        const paymentInfo = dependency.processPayment(data.amount);
        return paymentInfo;
    },

    processPayment: function(amount) {
        // API call to external payment gateway
        console.log("i am original processPayment function");
        return { id: "123", amount: amount };
    }
};

export { app };
