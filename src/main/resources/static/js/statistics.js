async function carHealthChart(carId) {
  const yearlyApexChartId = document.getElementById("car_health_chart");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [90],
      chart: {
        height: 400,
        type: "radialBar",
      },
      plotOptions: {
        radialBar: {
          hollow: {
            size: "60%",
          },
        },
      },
      labels: ["Car health"],
    };

    var chart = new ApexCharts(document.querySelector("#car_health_chart"), options);
    chart.render();
  }
}
async function totalHealthChart(carId) {
  const yearlyApexChartId = document.getElementById("total_health_chart");
  const resp = await fetch(`http://localhost:8080/api/maintenance/${carId}/years`);
  const data = await resp.json();
  const roundedValues = Object.values(data).map((v) => Math.round(v));
  if (yearlyApexChartId) {
    var options = {
      series: [76, 67, 61, 90],
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
      labels: ["Bremsen & Reifen", "Antrieb", "Fahrwerk", "Karosserie"],
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
          data: [44, 55, 57, 56, 61, 58, 63, 60, 66],
        },
        {
          name: "ähnliche Autos",
          data: [76, 85, 101, 98, 87, 105, 91, 114, 94],
        },
        {
          name: "Alle Autos",
          data: [35, 41, 36, 26, 45, 48, 52, 53, 41],
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
        categories: ["Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct"],
      },
      yaxis: {
        title: {
          text: "$ (thousands)",
        },
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
