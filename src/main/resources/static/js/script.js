// getCar stays the same
async function getCar(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/cars/${carId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);

    const car = await response.json();

    loadNextMtncDate(carId);
    loadTotalCost(carId);
    loadMaintenanceTable(carId);
    document.getElementById("makeYear").innerText = car.makeYear || "----";
    document.getElementById("kilometer").innerText = car.mileage || "----";
    document.getElementById("inspection").innerText =
      car.inspectionExp || "----";
    document.getElementById("carInfos").innerText =
      "Dein " +
        car.manufacturer +
        car.model +
        " (Baujahr" +
        car.makeYear +
        ") ist aktuell bei " +
        car.mileage +
        "km. Farbe: " +
        car.carColor +
        "." || "ERROR LOADING CAR INFO";
  } catch (error) {
    console.error("Error fetching car:", error);
  }
}

// getOwner - only name right now
async function getOwner(ownerId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/owners/${ownerId}`,
      {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      }
    );

    if (!response.ok) throw new Error("HTTP error " + response.status);

    const owner = await response.json();

    document.getElementById("ownerName").innerText =
      owner.firstName + " " + owner.lastName || "Benutzer";
    document.getElementById("ownerFirstName").innerText =
      owner.firstName + "!" || "Benutzer";
  } catch (error) {
    console.error("Error fetching owner:", error);
  }
}

// Chart functions

async function loadMaintenanceChart(carId, year) {
  try {
    const resp = await fetch(
      `http://localhost:8080/api/maintenance/${carId}/year/${year}`
    );
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
        fill: false,
      });
    }

    // add each type (may be an object with arrays)
    Object.keys(breakdown).forEach((typeKey) => {
      datasets.push({
        label: typeKey,
        data: breakdown[typeKey] || [],
        borderColor: Black,
        tension: 0.2,
        fill: false,
      });
    });

    window.costChartInstance = new Chart(ctx, {
      type: "line",
      data: { labels, datasets },
      options: {
        responsive: true,
        plugins: {
          title: {
            display: true,
            text: `Maintenance Costs for ${year} (car ${carId})`,
          },
          tooltip: { mode: "index", intersect: false },
        },
        interaction: { mode: "nearest", axis: "x", intersect: false },
        scales: {
          y: { beginAtZero: true, title: { display: true, text: "Cost" } },
          x: { title: { display: true, text: "Month" } },
        },
      },
    });
  } catch (err) {
    console.error("Error loading maintenance chart:", err);
    alert(
      "Could not load chart — check console (F12) and network tab for the API request."
    );
  }
}

function showChart() {
  const carId = parseInt(document.getElementById("carSelect").value, 10);
  const year = parseInt(document.getElementById("yearSelect").value, 10);
  loadMaintenanceChart(carId, year);
}

async function loadYearlyApexChart(carId) {
  const getTopBrowsingPagesPerMinuteId = document.getElementById(
    "top_browsing_pages_per_minute_chart"
  );
  const resp = await fetch(
    `http://localhost:8080/api/maintenance/${carId}/years`
  );
  const data = await resp.json();
  if (getTopBrowsingPagesPerMinuteId) {
    var options = {
      series: [
        {
          name: "Yearly Maintenance Costs",
          data: Object.values(data),
        },
      ],
      colors: ["#2ED47E"],
      chart: {
        type: "bar",
        height: 180,
        stacked: true,
        toolbar: {
          show: false,
        },
        zoom: {
          enabled: false,
        },
      },
      plotOptions: {
        bar: {
          horizontal: false,
          borderRadius: 0,
          borderRadiusApplication: "end",
          borderRadiusWhenStacked: "last",
          columnWidth: "50%",
          barHeight: "30%",
          dataLabels: {
            enabled: true,
            distributed: false,
            total: {
              enabled: true,
              offsetY: -10,
              style: {
                color: "#919AA3",
                fontSize: "14px",
                fontFamily: "Outfit",
                fontWeight: 400,
              },
            },
          },
        },
      },
      dataLabels: {
        enabled: false,
      },
      xaxis: {
        categories: Object.keys(data),
        axisTicks: {
          show: true,
          color: "#ECEEF2",
        },
        axisBorder: {
          show: false,
          color: "#ECEEF2",
        },
        labels: {
          show: true,
          style: {
            colors: "#8695AA",
            fontSize: "14px",
            fontFamily: "Outfit",
          },
        },
      },
      yaxis: {
        labels: {
          show: false,
          style: {
            colors: "#9C9AB6",
            fontSize: "14px",
            fontFamily: "Outfit",
            fontWeight: 500,
          },
        },
        axisBorder: {
          show: false,
          color: "#ECEEF2",
        },
        axisTicks: {
          show: false,
          color: "#ECEEF2",
        },
      },
      legend: {
        position: "right",
        offsetY: 40,
        show: false,
      },
      fill: {
        opacity: 1,
      },

      grid: {
        borderColor: "#F5F5F5",
        strokeDashArray: 0,
        xaxis: {
          lines: {
            show: false,
          },
        },
      },
    };

    var chart = new ApexCharts(
      document.querySelector("#top_browsing_pages_per_minute_chart"),
      options
    );
    chart.render();
  }
}

async function loadYearlyChart(carId) {
  const resp = await fetch(
    `http://localhost:8080/api/maintenance/${carId}/years`
  );
  const data = await resp.json();

  const ctx = document.getElementById("costChart").getContext("2d");

  if (window.costChartInstance) {
    window.costChartInstance.destroy();
  }

  window.costChartInstance = new Chart(ctx, {
    type: "bar",
    data: {
      labels: Object.keys(data),
      datasets: [
        {
          label: "Total Cost per Year",
          data: Object.values(data),
          backgroundColor: "#CDE7B0",
        },
      ],
    },
    options: {
      responsive: true,
      plugins: {
        title: { display: true, text: "Yearly Maintenance Costs" },
      },
      scales: {
        y: { beginAtZero: true, title: { display: true, text: "Cost (€)" } },
        x: { title: { display: true, text: "Year" } },
      },
    },
  });
}

async function loadTotalCost(carId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/maintenance/totals/${carId}`
    );
    if (!response.ok) throw new Error("HTTP error " + response.status);
    const data = await response.json();

    // Update the DOM
    document.getElementById(
      "totalCost"
    ).innerText = `€ ${data.totalCost.toFixed(2)}`;
    document.getElementById(
      "averageCost"
    ).innerText = `€ ${data.averageCost.toFixed(2)}`;
  } catch (error) {
    console.error("Error fetching total costs:", error);
  }
}

async function loadNextMtncDate(carId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/maintenance/nextMTNC/${carId}`
    );
    if (!response.ok) throw new Error("HTTP error " + response.status);
    const data = await response.json();

    // Update the DOM
    if (data) {
      document.getElementById("nextMtnc").innerText = new Date(
        data.nextDate
      ).toLocaleDateString();
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
    const response = await fetch(
      `http://localhost:8080/api/maintenance/table/${carId}`
    );
    const data = await response.json();

    const tableBody = document.getElementById("maintenanceTableBody");
    tableBody.innerHTML = "";

    data.forEach((entry) => {
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
    const response = await fetch(
      `http://localhost:8080/api/maintenance/costComparison/${carId}`
    );
    const data = await response.json();

    const ctx = document.getElementById("visit_by_day_chart").getContext("2d");
    new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["This Car", "Other Cars (avg)"],
        datasets: [
          {
            label: "Total Costs (€)",
            data: [data.myCarTotal, data.otherCarsAverage],
            backgroundColor: ["#CDE7B0", "#CDE7B0"],
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
          title: {
            display: true,
            text: "Total Maintenance Costs Comparison",
          },
        },
      },
    });
  } catch (error) {
    console.error("Error loading cost comparison:", error);
  }
}

async function loadAverageCostComparison(carId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/maintenance/averageCostComparison/${carId}`
    );
    const data = await response.json();

    const ctx = document
      .getElementById("AverageCostComparisonChart")
      .getContext("2d");
    new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["This Car", "Other Cars (avg)"],
        datasets: [
          {
            label: "Average Cost per Repair (€)",
            data: [data.myCarAverage, data.otherCarsAverage],
            backgroundColor: ["#CDE7B0", "#CDE7B0"],
          },
        ],
      },
      options: {
        responsive: true,
        plugins: {
          legend: { display: false },
          title: {
            display: true,
            text: "Average Cost per Repair Comparison",
          },
        },
      },
    });
  } catch (error) {
    console.error("Error loading average cost comparison:", error);
  }
}

async function loadCarHealth(carId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/maintenance/critical/${carId}`
    );
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
        datasets: [
          {
            data: [Issues, Health],
            backgroundColor: ["#eeb8b8ff", "#CDE7B0"],
            borderWidth: 0,
            cutout: "60%",
          },
        ],
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
            padding: { top: 5, bottom: 5 },
          },
        },
        rotation: -90,
        circumference: 180,
      },
    });
  } catch (error) {
    console.error("Error loading car health:", error);
  }
}

// Initial load for car and owner with dynamic ID
document.addEventListener("DOMContentLoaded", async () => {
  try {
    const res = await fetch("/api/cars/default");
    if (!res.ok) throw new Error("No default car set");
    const defaultCar = await res.json();
    const defaultCarId = defaultCar.carId;
    const ownerId = defaultCar.owner.ownerId;
    console.log("Default car ID:", defaultCarId);
    console.log("Owner ID:", ownerId);
    console.log(defaultCar);
    getCar(defaultCarId);
    getOwner(ownerId);
    loadCostComparison(defaultCarId);
    loadAverageCostComparison(defaultCarId);
    loadYearlyChart(defaultCarId);
    loadCarHealth(defaultCarId);
    loadYearlyApexChart(defaultCarId);
    loadMaintenanceTable(defaultCarId);
    loadCarSelection();
  } catch (error) {
    console.error("Error loading default car:", error);
  }
});
//load car selection cards for owner page dynamically
async function loadCarSelection() {
  try {
    const response = await fetch("http://localhost:8080/api/cars");
    if (!response.ok) throw new Error("HTTP error " + response.status);
    const cars = await response.json();
    const container = document.getElementById("carCardsContainer");
    cars.forEach((car) => {
      const cardHtml = `
        <div class="col-12" id="clickable" onclick="setDefaultCar(${
          car.carId
        }); window.location.href='/mydashboard.html'" style="cursor: pointer">
        <div
            class="card rounded-10 border-0 mb-4 bg-img zinnia-card"
            style="
                background: linear-gradient(
                101deg,
                #1D546C 55.73%,
                #0C2B4E 99.52%
                );
                padding: 30.5px 40px;
            ">
            <div class="row align-items-center">
                <div class="col-sm-6 col-lg-7">
                    <h2 class="fs-26 fw-normal text-white mb-3">
                        <span class="fw-900">${car.manufacturer} ${
        car.model
      }</span>
                    </h2>
                    <p
                        class="fs-16 lh-1-8 hospital-content"
                        style="color: #cbc7ff; margin-bottom: 40px">
                        Dein ${car.manufacturer} ${car.model} (Baujahr ${
        car.makeYear
      }) ist aktuell bei ${car.mileage.toLocaleString()} km. Farbe: ${
        car.carColor
      }.
                    </p>
                </div>
                <div class="col-sm-6 col-lg-5">
                    <div
                        class="text-center text-sm-end mt-4 mt-sm-0"
                        style="margin-right: 30px">
                        <img
                            src="assets/images/carph.png"
                            alt="Car Placeholder"
                            class="img-fluid"
                            style="max-width: 150px" />
                    </div>
                </div>
            </div>
        </div>
    </div>
    `;
      container.insertAdjacentHTML("afterbegin", cardHtml);
    });
  } catch (error) {
    console.error("Error fetching cars:", error);
  }
}
// Set default car
async function setDefaultCar(carId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/cars/default/${carId}`,
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
      }
    );
    if (!response.ok) throw new Error("HTTP error " + response.status);

    console.log("Default car set successfully!");
  } catch (error) {
    console.error("Error setting default car:", error);
    alert("Failed to set default car.");
  }
}
