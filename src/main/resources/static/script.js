// getCar stays the same
async function getCar(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/cars/${carId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" }
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);

    const car = await response.json();
    document.getElementById("manufacturer").innerText = car.manufacturer || "";
    document.getElementById("model").innerText = car.model || "";
    document.getElementById("baujahr").innerText = car.makeYear || "";
    document.getElementById("kilo").innerText = car.mileage || "";
    document.getElementById("pickerl").innerText = car.inspectionExp || "";
    document.getElementById("color").innerText = car.carColor || "";
    document.getElementById("vin").innerText = car.vinNumber || "";
  } catch (error) {
    console.error("Error fetching car:", error);
  }
}

// addOwner - keep as a separate top-level function (not nesting other functions inside it)
async function addOwner() {
  try {
    const response = await fetch("http://localhost:8080/api/owners", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        street: document.getElementById('street').value,
        houseNumber: document.getElementById('houseNumber').value,
        city: document.getElementById('city').value
      })
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);
    const result = await response.json();
    console.log("Server response:", result);

  } catch (error) {
    console.error("Error adding owner:", error);
  }
}

// -------- Chart functions (top-level, not nested) --------

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
        borderColor: getRandomColor(),
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

function getRandomColor() {
  return `hsl(${Math.floor(Math.random() * 360)}, 70%, 50%)`;
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
      labels: Object.keys(data),      // ["2023","2024","2025"]
      datasets: [{
        label: "Total Cost per Year",
        data: Object.values(data),   // [400,650,120]
        backgroundColor: "orange"
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