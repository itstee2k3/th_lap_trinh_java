// calculateTotal.js

document.addEventListener("DOMContentLoaded", function () {
    calculateTotals();
});

function calculateTotals() {
    var orderCards = document.querySelectorAll(".card-body"); // Select all order sections
    orderCards.forEach(function (card) {
        var totalPriceElements = card.getElementsByClassName("total-price");
        var totalOrderAmount = 0;
        for (var i = 0; i < totalPriceElements.length; i++) {
            totalOrderAmount += parseFloat(totalPriceElements[i].textContent);
        }
        var totalOrderAmountSpan = card.querySelector(".total-order-amount");
        totalOrderAmountSpan.textContent = totalOrderAmount.toFixed(2); // Display total amount with two decimal places
    });
}
