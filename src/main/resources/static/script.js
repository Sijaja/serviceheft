async function getCar(carId) {
  try {
    const response = await fetch(`http://localhost:8080/api/cars/${carId}`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    });

    if (!response.ok) {
      throw new Error("HTTP error " + response.status);
    }

    const car = await response.json();

    // Example: update DOM
    document.getElementById("manufacturer").innerText = car.manufacturer;
    document.getElementById("model").innerText = car.model;
    document.getElementById("baujahr").innerText = car.makeYear;
    document.getElementById("kilo").innerText = car.mileage;
    document.getElementById("pickerl").innerText = car.inspectionExp;
    document.getElementById("color").innerText = car.carColor;
    document.getElementById("vin").innerText = car.vinNumber;
  } catch (error) {
    console.error("Error:", error);
  }
}

async function addOwner() {
  try {
    const response = await fetch("http://localhost:8080/api/owners", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        street: document.getElementById('street').value,
        houseNumber: document.getElementById('houseNumber').value,
        city: document.getElementById('city').value
      })
    });

    if (!response.ok) {
      throw new Error("HTTP error " + response.status);
    }

    const result = await response.json();
    console.log("Server response:", result);

  } catch (error) {
    console.error("Error:", error);
  }
}