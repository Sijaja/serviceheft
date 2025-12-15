// getCar stays the same
async function getCar(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/cars/${carId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);
    const car = await response.json();
    document.getElementById("myImage").src = "assets/images/" + car.carType + ".png" || "assets/images/other.png";
    document.getElementById("makeYear").innerText = car.makeYear || "----";
    document.getElementById("kilometer").innerText = car.mileage || "----";
    document.getElementById("inspection").innerText = car.inspectionExp || "----";
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
    const response = await fetch(`http://localhost:8080/api/owners/${ownerId}`, {
      method: "GET",
      headers: { "Content-Type": "application/json" },
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);

    const owner = await response.json();

    document.getElementById("ownerName").innerText = owner.firstName + " " + owner.lastName || "Benutzer";
    document.getElementById("ownerFirstName").innerText = owner.firstName + "!" || "Benutzer";
  } catch (error) {
    console.error("Error fetching owner:", error);
  }
}
// Chart functions
function showChart() {
  const carId = parseInt(document.getElementById("carSelect").value, 10);
  const year = parseInt(document.getElementById("yearSelect").value, 10);
  loadMaintenanceChart(carId, year);
}
async function loadYearlyApexChart(carId) {
  const yearlyApexChartId = document.getElementById("yearly_apex_chart");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [
        {
          name: "Jährliche Wartungskosten",
          data: roundedValues,
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
        colors: "#1D546C",
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

    var chart = new ApexCharts(document.querySelector("#yearly_apex_chart"), options);
    chart.render();
  }
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
async function loadMaintenanceTable(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/maintenance/table/${carId}`);
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
// Initial load for car and owner with dynamic ID
document.addEventListener("DOMContentLoaded", async () => {
  try {
    const user = await fetch("/api/user");
    if (!user.ok) throw new Error("No user logged in yet");
    const loggedUser = await user.json();
    const owner = await fetch(`/api/owners/${loggedUser.id}`);
    if (!owner.ok) throw new Error("No owner found");
    const ownerData = await owner.json();
    const defaultCarId = ownerData.defaultCarId;
    console.log("Default car ID:", defaultCarId);
    console.log("Owner ID:", loggedUser.id);
    console.log(defaultCarId);
    getCar(defaultCarId);
    getOwner(loggedUser.id);
    loadYearlyApexChart(defaultCarId);
    loadMaintenanceTable(defaultCarId);
    tireDetails(defaultCarId);
    oilDetails(defaultCarId);
    batteryDetails(defaultCarId);
    totalHealthChart(defaultCarId);
    comparesionChart(defaultCarId);
  } catch (error) {
    console.error("Error loading default car:", error);
  }
});
// 3 Details for the dashboard cards: Tires, Oil, Battery
async function tireDetails(carId) {
  try {
    const response = await fetch(`/api/maintenance/owner/${carId}`);
    const maintenance = await response.json();
    const latestWithTiremeasurement = maintenance
      .slice()
      .reverse()
      .find((maintenance) => maintenance.tireCheck.treadFrontLeft != null);

    if (latestWithTiremeasurement) {
      document.getElementById("tires").innerText =
        (
          (latestWithTiremeasurement.tireCheck.treadFrontLeft +
            latestWithTiremeasurement.tireCheck.treadFrontRight +
            latestWithTiremeasurement.tireCheck.treadRearLeft +
            latestWithTiremeasurement.tireCheck.treadRearRight) /
          4
        ).toFixed(1) + " mm";
    } else {
      document.getElementById("tires").innerText = "N/A";
    }
  } catch (error) {
    console.error("Error fetching tire details:", error);
    document.getElementById("tires").innerText = "---";
  }
}
async function oilDetails(carId) {
  try {
    const response = await fetch(`/api/maintenance/owner/${carId}`);
    const maintenance = await response.json();
    const latestWithOilchange = maintenance
      .slice()
      .reverse()
      .find((m) => m.engineCheck?.oilReplaced === true);

    if (latestWithOilchange) {
      document.getElementById("oilDate").innerText = latestWithOilchange.mtncDate || "N/A";
      document.getElementById("oilKm").innerText = latestWithOilchange.currentMileage + " km" || "N/A";
    } else {
      document.getElementById("oilDate").innerText = "N/A";
      document.getElementById("oilKm").innerText = "N/A";
    }
  } catch (error) {
    console.error("Error fetching Oil details:", error);
    document.getElementById("oilDate").innerText = "---";
    document.getElementById("oilKm").innerText = "---";
  }
}
async function batteryDetails(carId) {
  try {
    const response = await fetch(`/api/maintenance/owner/${carId}`);
    const maintenance = await response.json();
    const latestWithOilchange = maintenance
      .slice()
      .reverse()
      .find((m) => m.electricCheck?.age != "NOT_CHECKED");

    if (latestWithOilchange) {
      document.getElementById("batteryAge").innerText = latestWithOilchange.electricCheck.age || "N/A";
      document.getElementById("batteryCurrent").innerText = latestWithOilchange.electricCheck.voltage + " V" || "N/A";
    } else {
      document.getElementById("batteryAge").innerText = "N/A";
      document.getElementById("batteryCurrent").innerText = "N/A";
    }
  } catch (error) {
    console.error("Error fetching Battery details:", error);
    document.getElementById("batteryAge").innerText = "---";
    document.getElementById("batteryCurrent").innerText = "---";
  }
}
async function editCar(carId, year) {
  try {
    const response = await fetch(`http://localhost:8080/api/cars/${carId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      credentials: "include",
      body: JSON.stringify({
        makeYear: year,
      }),
    });
    if (!response.ok) throw new Error("HTTP error " + response.status);
    const result = await response.json();
    console.log("Server response:", result);
  } catch (error) {
    console.error("Error editing car:", error);
  }
}
