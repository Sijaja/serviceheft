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
        <div class="col-12" id="clickable" onclick="handleCarClick(${car.carId})" style="cursor: pointer">
        <div
            class="card rounded-10 border-0 mb-4 bg-img zinnia-card position-relative"
            style="
                background: linear-gradient(
                101deg,
                #1D546C 55.73%,
                #0C2B4E 99.52%
                );
                padding: 30.5px 40px;
            ">
            <button 
            onclick="event.stopPropagation(); deleteCard(${car.carId})" 
            class="btn position-absolute"
            style="
                top: 15px;
                right: 15px;
                background: rgba(255, 255, 255, 0.1);
                border: 1px solid rgba(255, 255, 255, 0.2);
                color: white;
                width: 32px;
                height: 32px;
                padding: 0;
                border-radius: 50%;
                display: flex;
                align-items: center;
                justify-content: center;
                transition: all 0.3s ease;
            "
            onmouseover="this.style.background='rgba(255, 255, 255, 0.2)'"
            onmouseout="this.style.background='rgba(255, 255, 255, 0.1)'">
            <span class="material-symbols-outlined" style="font-size: 18px;">close</span>
        </button>
            <div class="row align-items-center">
                <div class="col-sm-6 col-lg-7">
                    <h2 class="fs-26 fw-normal text-white mb-3">
                        <span class="fw-900">${car.manufacturer} ${car.model}</span>
                    </h2>
                    <p
                        class="fs-16 lh-1-8 hospital-content"
                        style="color: #cbc7ff; margin-bottom: 40px">
                        Dein ${car.manufacturer} ${car.model} (Baujahr ${
        car.makeYear
      }) ist aktuell bei ${car.mileage.toLocaleString()} km. Farbe: ${car.carColor}.
                    </p>
                </div>
                <div class="col-sm-6 col-lg-5">
                    <div
                        class="text-center text-sm-end mt-4 mt-sm-0"
                        style="margin-right: 30px">
                        <img
                            src="assets/images/${car.carType}.png"
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
    const response = await fetch(`http://localhost:8080/api/cars/default/${carId}`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
    });
    if (!response.ok) throw new Error("HTTP error " + response.status);

    console.log("Default car set successfully!");
  } catch (error) {
    console.error("Error setting default car:", error);
    alert("Failed to set default car.");
  }
}

async function handleCarClick(carId) {
  // Optional: Show loading indicator
  const card = event.currentTarget;
  card.style.opacity = "0.6";
  card.style.pointerEvents = "none";

  try {
    await setDefaultCar(carId);
    window.location.href = "/mydashboard.html";
  } catch (error) {
    // Re-enable card if error occurs
    card.style.opacity = "1";
    card.style.pointerEvents = "auto";
  }
}

async function deleteCard(carId) {
  const newOwnerId = 0; // 0 means it will be assigned to the administrator account (so deleted from user view)
  const result = await Swal.fire({
    title: "Fahrzeug löschen?",
    text: "Diese Aktion kann nicht rückgängig gemacht werden!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#d33",
    cancelButtonColor: "#3085d6",
    confirmButtonText: "Ja, löschen!",
    cancelButtonText: "Abbrechen",
  });

  if (result.isConfirmed) {
    try {
      const response = await fetch(`http://localhost:8080/api/cars/${carId}/transfer/${newOwnerId}`, {
        method: "PUT",
        credentials: "include", // Important for authentication
      });

      if (!response.ok) {
        throw new Error("Fehler beim Löschen");
      }

      // Remove the card from DOM with animation
      const cardElement = document.getElementById(`car-${carId}`);
      if (cardElement) {
        cardElement.style.transition = "opacity 0.3s ease";
        cardElement.style.opacity = "0";
        setTimeout(() => cardElement.remove(), 300);
      }

      // Show success message
      Swal.fire("Gelöscht!", "Das Fahrzeug wurde erfolgreich gelöscht.", "success");

      window.location.reload();
    } catch (error) {
      console.error("Error deleting car:", error);
      Swal.fire("Fehler!", "Das Fahrzeug konnte nicht gelöscht werden.", "error");
    }
  }
}
