//selected car Id
let selectedCarId = null;

//First we validate the Vin number to unlock the rest of the Tabs:
async function validateVin() {
    try {
        const vin = document.getElementById('vin-input').value;
        const res = await fetch(`/api/cars/vin/${vin}`);
        const data = await res.json();
        selectedCarId = data.carId;
        if (res.ok) {
            document.getElementById('vin-input').style.borderColor = "green";
            document.getElementById('vin-input').setAttribute('readonly', true);
            document.getElementById('Riemen-tab').removeAttribute('disabled');
            document.getElementById('Karosseriekontrolle-tab').removeAttribute('disabled');
            document.getElementById('Bremsenkontrolle-tab').removeAttribute('disabled');
            document.getElementById('Elektrikdiagnose-tab').removeAttribute('disabled');
            document.getElementById('Abgasdiagnose-tab').removeAttribute('disabled');
            document.getElementById('Motordiagnose-tab').removeAttribute('disabled');
            document.getElementById('Filterkontrolle-tab').removeAttribute('disabled');
            document.getElementById('Klima-tab').removeAttribute('disabled');
            document.getElementById('Rostkontrolle-tab').removeAttribute('disabled');
            document.getElementById('Reifenkontrolle-tab').removeAttribute('disabled');
            document.getElementById('wartung-tab').removeAttribute('disabled');
        } 
    } catch (error) {
        document.getElementById('vin-input').style.borderColor = "red";
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

    BeltHoseCheck: {
      serpentineBelt: val("serpentineBelt-input"),
      timingBelt: val("Zahnriemen-input"),
      radiatorHoses: val("radiatorHoses-input"),
      heaterHoses: val("heaterHoses-input"),
    },

    BodyCheck: {
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
      rearWindow: val("rearWindow-input")
    },

    BrakeCheck: {
      frontPadThickness: val("fPadThickness-input"),
      rearPadThickness: val("rPadThickness-input"),
      frontRotors: val("frontRotorsCon-input"),
      rearRotors: val("rearRotorsCon-input"),
      brakeLines: val("brakeLines-input")
    },

    ElectricCheck: {
      voltage: val("voltage-input"),
      alternator: val("alternator-input"),
      terminals: val("terminals-input"),
      age: val("age-input"),
      headLights: val("headLights-input"),
      tailLight: val("tailLight-input"),
      turnSignals: val("turnSignals-input")
    },

    EmmisionCheck: {
      exhaust: val("exhaust-input"),
      catalytic: val("catalytic-input"),
      o2Sensors: val("o2Sensors-input")
    },

    EngineCheck: {
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
      engineStatus: val("engineStatus-input")
    },

    FilterCheck: {
      airFilter: val("airFilter-input"),
      cabinFilter: val("cabinFilter-input"),
      fuelFilter: val("fuelFilter-input")
    },

    HvacCheck: {
      acPerformance: val("acPerformance-input"),
      heatPerformance: val("heatPerformance-input"),
      blowerMotor: val("blowerMotor-input")
    },

    RustCheck: {
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
      suspension: val("suspension-input")
    },

    TireCheck: {
      treadFrontLeft: val("treadFrontLeft-input"),
      treadFrontRight: val("treadFrontRight-input"),
      treadRearLeft: val("treadRearLeft-input"),
      treadRearRight: val("treadRearRight-input"),
      pressureFL: val("pressureFL-input"),
      pressureFR: val("pressureFR-input"),
      pressureRL: val("pressureRL-input"),
      pressureRR: val("pressureRR-input"),
      wearPattern: val("wearPattern-input"),
      shockAbsorbers: val("shockAbsorbers-input")
    },
  };
}

//finally a call to the backend through to API point (this is the one I should call with the submit button)
async function submitMaintenance() {
  const payload = gatherMaintenanceData();

  const res = await fetch("/api/maintenance/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(payload)
  });

  if (res.ok) {
    alert("Maintenance saved!");
  } else {
    alert("Error saving maintenance");
  }
}