src="https://cdn.jsdelivr.net/npm/chart.js@4.4.6/dist/chart.umd.js"
function Action(newAction) {
    document.getElementById('budgetForm').action = newAction;
    document.getElementById('budgetForm').submit();
}

// Get the modal
var popup = document.getElementById("popup");

// Get the button that opens the modal
var btn = document.getElementById("openFormBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close-btn")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
    popup.style.display = "block";
}

span.onclick = function() {
    popup.style.display = "none";
}
window.BUDGETS
const Labels =Budgets.map((budget)=>budget.category)
const data =Budgets.map((budget)=>budget.budget)
const ctx = document.getElementById('myChart').getContext('2d');
const myPieChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
        labels:Labels ,
        datasets: [{
            label: Labels,
            data:data ,
            backgroundColor: [
            ],
        }]
    },
    options: {
        maintainAspectRatio: false,
        responsive: true,
        plugins: {
            legend: {
                position: 'top',
            },
            title: {
                display: true,
                text: 'Mein Kreisdiagramm'
            }
        }
    }
});