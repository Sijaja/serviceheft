// getCar stays the same
async function getCar(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/cars/${carId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" }
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);

    const car = await response.json();
    const carImage = document.getElementById("carImage");

    if (car.photoId) {
      carImage.src = "img/Cars/" + car.photoId + ".jpg";
      carImage.alt = car.manufacturer + " " + car.model;
    } else {
      carImage.src = "img/Cars/default.jpg";
    }
    loadNextMtncDate(carId);
    document.getElementById("manufacturer").innerText = car.manufacturer || "";
    document.getElementById("model").innerText = car.model || "";
    document.getElementById("baujahr").innerText = car.makeYear || "";
    document.getElementById("kilo").innerText = car.mileage || "";
    document.getElementById("pickerl").innerText = car.inspectionExp || "";
    document.getElementById("color").innerText = car.carColor || "";
    document.getElementById("vin").innerText = car.vinNumber || "";
    loadTotalCost(carId);
    loadMaintenanceTable(carId);
  } catch (error) {
    console.error("Error fetching car:", error);
  }
}

// Chart functions (top-level, not nested) 

async function loadMaintenanceChart(carId, year) {
  try {
    const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/year/${year}`);
    if (!resp.ok) throw new Error(`HTTP ${resp.status} ${resp.statusText}`);

    const data = await resp.json();

    // support multiple possible backend field names
    const labels = data.months || [];
    const totals = data.costs || data.monthlyTotals || [];
    const breakdown = data.types || data.typeBreakdown || {};

    const ctx = document.getElementById("costChart").getContext("2d");

    // Destroy old chart if exists
    if (window.costChartInstance) {
      window.costChartInstance.destroy();
    }

    const datasets = [];

    if (Array.isArray(totals) && totals.length) {
      datasets.push({
        label: "Total Costs",
        data: totals,
        borderColor: "blue",
        tension: 0.2,
        fill: false
      });
    }

    // add each type (may be an object with arrays)
    Object.keys(breakdown).forEach(typeKey => {
      datasets.push({
        label: typeKey,
        data: breakdown[typeKey] || [],
        borderColor: Black,
        tension: 0.2,
        fill: false
      });
    });

    window.costChartInstance = new Chart(ctx, {
      type: "line",
      data: { labels, datasets },
      options: {
        responsive: true,
        plugins: {
          title: { display: true, text: `Maintenance Costs for ${year} (car ${carId})` },
          tooltip: { mode: 'index', intersect: false }
        },
        interaction: { mode: 'nearest', axis: 'x', intersect: false },
        scales: {
          y: { beginAtZero: true, title: { display: true, text: 'Cost' } },
          x: { title: { display: true, text: 'Month' } }
        }
      }
    });

  } catch (err) {
    console.error("Error loading maintenance chart:", err);
    alert("Could not load chart — check console (F12) and network tab for the API request.");
  }
}

function showChart() {
  const carId = parseInt(document.getElementById("carSelect").value, 10);
  const year = parseInt(document.getElementById("yearSelect").value, 10);
  loadMaintenanceChart(carId, year);
}

async function loadYearlyChart(carId) {
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();

  const ctx = document.getElementById("costChart").getContext("2d");

  if (window.costChartInstance) {
    window.costChartInstance.destroy();
  }

  window.costChartInstance = new Chart(ctx, {
    type: "bar",
    data: {
      labels: Object.keys(data),
      datasets: [{
        label: "Total Cost per Year",
        data: Object.values(data),
        backgroundColor: "#CDE7B0"
      }]
    },
    options: {
      responsive: true,
      plugins: {
        title: { display: true, text: "Yearly Maintenance Costs" }
      },
      scales: {
        y: { beginAtZero: true, title: { display: true, text: "Cost (€)" } },
        x: { title: { display: true, text: "Year" } }
      }
    }
  });
}

async function loadTotalCost(carId) {
    try {
        const response = await fetch(`http://localhost:8080/api/maintenance/totals/${carId}`);
        if (!response.ok) throw new Error("HTTP error " + response.status);
        const data = await response.json();

        // Update the DOM
        document.getElementById("totalCost").innerText = `€ ${data.totalCost.toFixed(2)}`;
        document.getElementById("averageCost").innerText = `€ ${data.averageCost.toFixed(2)}`;

    } catch (error) {
        console.error("Error fetching total costs:", error);
    }
}

async function loadNextMtncDate(carId) {
    try {
        const response = await fetch(`http://localhost:8080/api/maintenance/nextMTNC/${carId}`);
        if (!response.ok) throw new Error("HTTP error " + response.status);
        const data = await response.json();

        // Update the DOM
        if (data) {
          document.getElementById("nextMtnc").innerText = new Date(data.nextDate).toLocaleDateString();
          document.getElementById("nextMileage").innerText = data.nextMileage;
        } else {
          document.getElementById("nextMtnc").innerText = "-";
          document.getElementById("nextMileage").innerText = "-";
}

    } catch (error) {
        console.error("Error fetching next:", error);
    }
}

async function loadMaintenanceTable(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/maintenance/table/${carId}`);
    const data = await response.json();

    const tableBody = document.getElementById("maintenanceTableBody");
    tableBody.innerHTML = "";

    data.forEach(entry => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${entry.date}</td>
        <td>${entry.description}</td>
        <td>${entry.cost.toFixed(2)} €</td>
        <td>${entry.mileage} km</td>
        <td>${entry.workshop}</td>
      `;
      tableBody.appendChild(row);
    });

  } catch (error) {
    console.error("Error loading maintenance table:", error);
  }
}

async function loadCostComparison(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/maintenance/costComparison/${carId}`);
    const data = await response.json();

    const ctx = document.getElementById("costComparisonChart").getContext("2d");
    new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["This Car", "Other Cars (avg)"],
        datasets: [{
          label: "Total Costs (€)",
          data: [data.myCarTotal, data.otherCarsAverage],
          backgroundColor: ["#CDE7B0", "#CDE7B0"]
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
          title: {
            display: true,
            text: "Total Maintenance Costs Comparison"
          }
        }
      }
    });
  } catch (error) {
    console.error("Error loading cost comparison:", error);
  }
}

async function loadAverageCostComparison(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/maintenance/averageCostComparison/${carId}`);
    const data = await response.json();

    const ctx = document.getElementById("AverageCostComparisonChart").getContext("2d");
    new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["This Car", "Other Cars (avg)"],
        datasets: [{
          label: "Average Cost per Repair (€)",
          data: [data.myCarAverage, data.otherCarsAverage],
          backgroundColor: ["#CDE7B0", "#CDE7B0"]
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
          title: {
            display: true,
            text: "Average Cost per Repair Comparison"
          }
        }
      }
    });
  } catch (error) {
    console.error("Error loading average cost comparison:", error);
  }
}

async function loadCarHealth(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/maintenance/critical/${carId}`);
    if (!response.ok) throw new Error("HTTP error " + response.status);
    const data = await response.json();

    const issueCount = data.length;
    const maxIssues = 6;
    const Issues = Math.max(0, Math.min(100, (issueCount / maxIssues) * 100));
    const Health = 100 - Issues;
    const ctx = document.getElementById("carStatusGauge").getContext("2d");

    // Destroy old chart if it exists
    if (window.carGaugeChart) window.carGaugeChart.destroy();

    window.carGaugeChart = new Chart(ctx, {
      type: "doughnut",
      data: {
        labels: ["Issues", "Health"],
        datasets: [{
          data: [Issues, Health],
          backgroundColor: [
            "#eeb8b8ff",
            "#CDE7B0"
          ],
          borderWidth: 0,
          cutout: "60%"
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: true },
          tooltip: { enabled: true },
          title: {
            display: true,
            text: `${Health.toFixed(1)}% Healthy`,
            position: "top",
            font: { size: 16 },
            padding: { top: 5, bottom: 5 }
          }
        },
        rotation: -90,
        circumference: 180
      }
    });

  } catch (error) {
    console.error("Error loading car health:", error);
  }
}

// Initial load for car with dynamic ID
document.addEventListener("DOMContentLoaded", async () => {
  try {
    const res = await fetch("/api/cars/default");
    if (!res.ok) throw new Error("No default car set");
    const defaultCar = await res.json();
    const defaultCarId = defaultCar.carId;
    console.log("Default car ID:", defaultCarId);
    console.log(defaultCar);
      getCar(defaultCarId);
      loadCostComparison(defaultCarId);
      loadAverageCostComparison(defaultCarId);
      loadYearlyChart(defaultCarId);
      loadCarHealth(defaultCarId);
  } catch (error) {
    console.error("Error loading default car:", error);
    alert("Could not load default car data. Please select a car.");
  }
  });