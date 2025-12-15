//selected car Id
let selectedCarId = null;

//Next button functionality
function nextTab() {
  const activeTab = document.querySelector(".nav-tabs .nav-link.active");
  const nextLi = activeTab.closest("li").nextElementSibling;

  if (nextLi) {
    nextLi.querySelector(".nav-link").click();
  }
}
//Previous button functionality
function prevTab() {
  const activeTab = document.querySelector(".nav-tabs .nav-link.active");
  const prevLi = activeTab.closest("li").previousElementSibling;

  if (prevLi) {
    prevLi.querySelector(".nav-link").click();
  }
}
document.querySelectorAll(".btn-next").forEach((btn) => {
  btn.addEventListener("click", nextTab);
});

document.querySelectorAll(".btn-prev").forEach((btn) => {
  btn.addEventListener("click", prevTab);
});

//First we validate the Vin number to unlock the rest of the Tabs:
async function validateVin() {
  try {
    const vin = document.getElementById("vin-input").value;
    const res = await fetch(`/api/cars/vin/${vin}`);
    const data = await res.json();
    selectedCarId = data.carId;
    if (res.ok) {
      document.getElementById("vin-input").style.borderColor = "green";
      document.getElementById("vin-input").setAttribute("readonly", true);
      document.getElementById("Riemen-tab").removeAttribute("disabled");
      document.getElementById("Karosseriekontrolle-tab").removeAttribute("disabled");
      document.getElementById("Bremsenkontrolle-tab").removeAttribute("disabled");
      document.getElementById("Elektrikdiagnose-tab").removeAttribute("disabled");
      document.getElementById("Abgasdiagnose-tab").removeAttribute("disabled");
      document.getElementById("Motordiagnose-tab").removeAttribute("disabled");
      document.getElementById("Filterkontrolle-tab").removeAttribute("disabled");
      document.getElementById("Klima-tab").removeAttribute("disabled");
      document.getElementById("Rostkontrolle-tab").removeAttribute("disabled");
      document.getElementById("Reifenkontrolle-tab").removeAttribute("disabled");
      document.getElementById("costs-tab").removeAttribute("disabled");
      document.getElementById("wartung-tab").removeAttribute("disabled");
    }
  } catch (error) {
    document.getElementById("vin-input").style.borderColor = "red";
    console.error(error);
  }
}

//a function to get the value of the fields
function val(id) {
  return document.getElementById(id)?.value ?? null;
}

//a function to cellect the info and warp it in one json file
function gatherMaintenanceData() {
  return {
    carId: selectedCarId,
    carCondition: val("carCondition-input"),
    mtncDate: val("mtncDate-input"),
    nextDate: val("nextDate-input"),
    currentMileage: val("currentMileage-input"),
    nextMileage: val("nextMileage-input"),
    cost: val("cost-input"),
    mtncType: val("mtncType-input"),
    inspectionNotes: val("inspectionNotes-input"),

    beltHoseCheck: {
      serpentineBelt: val("serpentineBelt-input"),
      timingBelt: val("Zahnriemen-input"),
      radiatorHoses: val("radiatorHoses-input"),
      heaterHoses: val("heaterHoses-input"),
    },

    bodyCheck: {
      hood: val("hood-input"),
      frontBumper: val("frontBumper-input"),
      rearBumper: val("rearBumper-input"),
      leftFrontDoor: val("leftFrontDoor-input"),
      leftRearDoor: val("leftRearDoor-input"),
      rightFrontDoor: val("rightFrontDoor-input"),
      rightRearDoor: val("rightRearDoor-input"),
      leftFrontFender: val("leftFrontFender-input"),
      rightFrontFender: val("rightFrontFender-input"),
      leftRearFender: val("leftRearFender-input"),
      rightRearFender: val("rightRearFender-input"),
      roof: val("roof-input"),
      trunk: val("trunk-input"),
      windshield: val("windshield-input"),
      rearWindow: val("rearWindow-input"),
    },

    brakeCheck: {
      fPadThickness: val("fPadThickness-input"),
      rPadThickness: val("rPadThickness-input"),
      frontRotorsCon: val("frontRotorsCon-input"),
      rearRotorsCon: val("rearRotorsCon-input"),
      brakeLines: val("brakeLines-input"),
    },

    electricCheck: {
      voltage: val("voltage-input"),
      alternatorOutput: val("alternator-input"),
      terminals: val("terminals-input"),
      age: val("age-input"),
      headLights: val("headLights-input"),
      tailLight: val("tailLight-input"),
      turnSignals: val("turnSignals-input"),
    },

    emmisionCheck: {
      exhaust: val("exhaust-input"),
      catalytic: val("catalytic-input"),
      o2Sensors: val("o2Sensors-input"),
    },

    engineCheck: {
      oilLevel: val("oilLevel-input"),
      oilCondition: val("oilCondition-input"),
      oilFilter: val("oilFilter-input"),
      oilReplaced: val("oilReplaced-input"),
      coolantLevel: val("coolantLevel-input"),
      coolantCondition: val("coolantCondition-input"),
      brakeFluidLevel: val("brakeFluidLevel-input"),
      brakeFluidColor: val("brakeFluidColor-input"),
      steeringFluid: val("steeringFluid-input"),
      gearFluid: val("gearFluid-input"),
      washFluid: val("washFluid-input"),
      engineStatus: val("engineStatus-input"),
    },

    filterCheck: {
      airFilter: val("airFilter-input"),
      cabinFilter: val("cabinFilter-input"),
      fuelFilter: val("fuelFilter-input"),
    },

    hvacCheck: {
      acPerformance: val("acPerformance-input"),
      heatPerformance: val("heatPerformance-input"),
      blowerMotor: val("blowerMotor-input"),
    },

    rustCheck: {
      wheelArches: val("wheelArches-input"),
      sideSkirts: val("sideSkirts-input"),
      doorBottom: val("doorBottom-input"),
      trunkFloor: val("trunkFloor-input"),
      hoodEdges: val("hoodEdges-input"),
      roofEdges: val("roofEdges-input"),
      fenders: val("fenders-input"),
      exhaustArea: val("exhaustArea-input"),
      underbody: val("underbody-input"),
      windowSeals: val("windowSeals-input"),
      suspension: val("suspension-input"),
    },

    tireCheck: {
      treadFrontLeft: val("treadFrontLeft-input"),
      treadFrontRight: val("treadFrontRight-input"),
      treadRearLeft: val("treadRearLeft-input"),
      treadRearRight: val("treadRearRight-input"),
      pressureFL: val("pressureFL-input"),
      pressureFR: val("pressureFR-input"),
      pressureRL: val("pressureRL-input"),
      pressureRR: val("pressureRR-input"),
      wearPattern: val("wearPattern-input"),
      shockAbsorbers: val("shockAbsorbers-input"),
    },

    costs: {
      beltsHosesCost: val("beltCost-input"),
      bodyPartsCost: val("bodyPartsCost-input"),
      brakesCost: val("brakeCost-input"),
      electricCost: val("electricCost-input"),
      engineCost: val("motorCost-input"),
      exhaustCost: val("abgasCost-input"),
      filtersCost: val("filterCost-input"),
      hvacCost: val("hvacCost-input"),
      rostCost: val("rostCost-input"),
      tiresCost: val("tireCost-input"),
    },
  };
}

//finally a call to the backend through to API point (this is the one I should call with the submit button)
async function submitMaintenance() {
  const payload = gatherMaintenanceData();

  const res = await fetch("/api/maintenance/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload),
  });

  if (res.ok) {
    const aprroved = await Swal.fire({
      title: "Geschaft!",
      text: "Wartung erfolgreich hinzugefügt!",
      icon: "success",
      confirmButtonColor: "#d33",
      confirmButtonText: "OK",
    });

    if (aprroved.isConfirmed) {
      window.location.href = "./wsdashboard.html";
    }
  } else {
    Swal.fire({
      title: "Fehler!",
      text: "beim Hinzufügen der Wartung",
      icon: "error",
    });
  }
}

function calculateTotal() {
  const beltCost = parseFloat(document.getElementById('beltCost-input').value) || 0;
  const bodyPartsCost = parseFloat(document.getElementById('bodyPartsCost-input').value) || 0;
  const brakeCost = parseFloat(document.getElementById('brakeCost-input').value) || 0;
  const electricCost = parseFloat(document.getElementById('electricCost-input').value) || 0;
  const motorCost = parseFloat(document.getElementById('motorCost-input').value) || 0;
  const abgasCost = parseFloat(document.getElementById('abgasCost-input').value) || 0;
  const filterCost = parseFloat(document.getElementById('filterCost-input').value) || 0;
  const hvacCost = parseFloat(document.getElementById('hvacCost-input').value) || 0;
  const rostCost = parseFloat(document.getElementById('rostCost-input').value) || 0;
  const tireCost = parseFloat(document.getElementById('tireCost-input').value) || 0;
  
  const total = beltCost + bodyPartsCost + brakeCost + electricCost + motorCost + abgasCost + filterCost + hvacCost + rostCost + tireCost;
  
  document.getElementById('cost-input').value = total.toFixed(2);
}


function calculateNextService() {
  const currentDate = document.getElementById('mtncDate-input').value;
  
  const currentMileage = parseFloat(document.getElementById('currentMileage-input').value) || 0;
  
  if (currentDate) {
    const nextDate = new Date(currentDate);
    nextDate.setMonth(nextDate.getMonth() + 12);
    
    const formattedDate = nextDate.toISOString().split('T')[0];
    document.getElementById('nextDate-input').value = formattedDate;
  }
  
  if (currentMileage > 0) {
    const nextMileage = currentMileage + 15000;
    document.getElementById('nextMileage-input').value = nextMileage;
  }
}