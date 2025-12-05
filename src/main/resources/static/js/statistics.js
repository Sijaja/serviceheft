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
    totalHealthChart(defaultCarId);
    comparesionChart(defaultCarId);
    MiniComparesionChartCost(defaultCarId);
    MiniComparesionChartCostPerK(defaultCarId);
    MiniComparesionChartNoM(defaultCarId);
    MiniComparesionChartMileage(defaultCarId);
  } catch (error) {
    console.error("Error loading default car:", error);
  }
});

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

async function totalHealthChart(carId) {
  const yearlyApexChartId = document.getElementById("total_health_chart");
  const resp = await fetch(`http://localhost:8080/api/maintenance/carHealth/${carId}`);
  console.log(resp);
  const data = await resp.json();
  console.log(data);
  if (yearlyApexChartId) {
    var options = {
      series: [data.brakesAndTires, data.drivetrain, data.belt, data.chasis],
      chart: {
        height: 390,
        type: "radialBar",
      },
      plotOptions: {
        radialBar: {
          offsetY: 0,
          startAngle: 0,
          endAngle: 270,
          hollow: {
            margin: 5,
            size: "30%",
            background: "transparent",
            image: undefined,
          },
          dataLabels: {
            name: {
              show: false,
            },
            value: {
              show: false,
            },
          },
          barLabels: {
            enabled: true,
            useSeriesColors: true,
            offsetX: -8,
            fontSize: "16px",
            formatter: function (seriesName, opts) {
              return seriesName + ":  " + opts.w.globals.series[opts.seriesIndex];
            },
          },
        },
      },
      colors: ["#1ab7ea", "#0084ff", "#39539E", "#0077B5"],
      labels: ["Bremsen & Reifen", "Motor & Getriebe", "Riemen & Schläuche", "Karosserie"],
      responsive: [
        {
          breakpoint: 480,
          options: {
            legend: {
              show: false,
            },
          },
        },
      ],
    };

    var chart = new ApexCharts(document.querySelector("#total_health_chart"), options);
    chart.render();
  }
  const carHealth = document.getElementById("car_health_chart");
  if (carHealth) {
    var options = {
      series: [data.totalScore],
      chart: {
        height: 250,
        type: "radialBar",
      },
      plotOptions: {
        radialBar: {
          hollow: {
            size: "50%",
          },
        },
      },
      labels: ["Car health"],
    };

    var chart2 = new ApexCharts(document.querySelector("#car_health_chart"), options);
    chart2.render();
  }
}

async function comparesionChart(carId) {
  const yearlyApexChartId = document.getElementById("cost_compare_chart");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [
        {
          name: "Mein Auto",
          data: [44, 55, 57, 56, 61, 58, 63],
        },
        {
          name: "ähnliche Autos",
          data: [76, 85, 101, 98, 87, 105, 91],
        },
        {
          name: "Alle Autos",
          data: [35, 41, 36, 26, 45, 48, 52],
        },
      ],
      chart: {
        type: "bar",
        height: 350,
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          borderRadius: 5,
          borderRadiusApplication: "end",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"],
      },
      xaxis: {
        categories: ["Motor", "Getriebe", "Karosserie", "Reimen", "Schläuche", "Bremsen", "Reifen"],
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          },
        },
      },
    };

    var chart = new ApexCharts(document.querySelector("#cost_compare_chart"), options);
    chart.render();
  }
}

async function MiniComparesionChartCost(carId) {
  const yearlyApexChartId = document.getElementById("cost_compare_chart_mini");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [
        {
          name: "Mein Auto",
          data: [44],
        },
        {
          name: "ähnliche Autos",
          data: [76],
        },
        {
          name: "Alle Autos",
          data: [35],
        },
      ],
      chart: {
        type: "bar",
        height: 350,
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          borderRadius: 5,
          borderRadiusApplication: "end",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"],
      },
      xaxis: {
        categories: ["GesamtKosten"],
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          },
        },
      },
    };

    var chart = new ApexCharts(document.querySelector("#cost_compare_chart_mini"), options);
    chart.render();
  }
}
async function MiniComparesionChartNoM(carId) {
  const yearlyApexChartId = document.getElementById("cost_compare_chart_count");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [
        {
          name: "Mein Auto",
          data: [44],
        },
        {
          name: "ähnliche Autos",
          data: [76],
        },
        {
          name: "Alle Autos",
          data: [35],
        },
      ],
      chart: {
        type: "bar",
        height: 350,
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          borderRadius: 5,
          borderRadiusApplication: "end",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"],
      },
      xaxis: {
        categories: ["Wartungsanzahl"],
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          },
        },
      },
    };

    var chart = new ApexCharts(document.querySelector("#cost_compare_chart_count"), options);
    chart.render();
  }
}
async function MiniComparesionChartMileage(carId) {
  const yearlyApexChartId = document.getElementById("cost_compare_chart_mileage");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [
        {
          name: "Mein Auto",
          data: [44],
        },
        {
          name: "ähnliche Autos",
          data: [76],
        },
        {
          name: "Alle Autos",
          data: [35],
        },
      ],
      chart: {
        type: "bar",
        height: 350,
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          borderRadius: 5,
          borderRadiusApplication: "end",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"],
      },
      xaxis: {
        categories: ["Kilometerstand"],
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          },
        },
      },
    };

    var chart = new ApexCharts(document.querySelector("#cost_compare_chart_mileage"), options);
    chart.render();
  }
}
async function MiniComparesionChartCostPerK(carId) {
  const yearlyApexChartId = document.getElementById("cost_compare_chart_per_k");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [
        {
          name: "Mein Auto",
          data: [44],
        },
        {
          name: "ähnliche Autos",
          data: [76],
        },
        {
          name: "Alle Autos",
          data: [35],
        },
      ],
      chart: {
        type: "bar",
        height: 350,
      },
      plotOptions: {
        bar: {
          horizontal: false,
          columnWidth: "55%",
          borderRadius: 5,
          borderRadiusApplication: "end",
        },
      },
      dataLabels: {
        enabled: false,
      },
      stroke: {
        show: true,
        width: 2,
        colors: ["transparent"],
      },
      xaxis: {
        categories: ["Kosten pro 1000Km"],
      },
      fill: {
        opacity: 1,
      },
      tooltip: {
        y: {
          formatter: function (val) {
            return "$ " + val + " thousands";
          },
        },
      },
    };

    var chart = new ApexCharts(document.querySelector("#cost_compare_chart_per_k"), options);
    chart.render();
  }
}
