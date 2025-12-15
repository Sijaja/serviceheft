document.addEventListener("DOMContentLoaded", async () => {
  try {
    const user = await fetch("/api/user");
    if (!user.ok) throw new Error("No user logged in yet");
    const loggedUser = await user.json();
    console.log("Owner ID:", loggedUser.id);
  } catch (error) {
    console.error("Error loading default car:", error);
  }
});

async function addCar(event) {
  event.preventDefault();
  try {
    const response = await fetch("http://localhost:8080/api/cars", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      credentials: "include",
      body: JSON.stringify({
        photoId: 0,
        vinNumber: document.getElementById("vinNumber").value,
        carColor: document.getElementById("color").value,
        manufacturer: document.getElementById("manufacturer").value,
        model: document.getElementById("model").value,
        inspectionExp: document.getElementById("inspection").value,
        makeYear: document.getElementById("makeYear").value,
        mileage: document.getElementById("mileage").value,
        carType: document.getElementById("carType").value,
      }),
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);
    const result = await response.json();
    document.querySelector("form").reset();
    const aprroved = await Swal.fire({
      title: "Geschaft!",
      text: "Das Fahrzeug wurde erfolgreich hinzugefügt!",
      icon: "success",
      confirmButtonColor: "#d33",
      confirmButtonText: "OK",
    });

    if (aprroved.isConfirmed) {
      window.location.href = "/car-selection.html";
    }
    console.log("Server response:", result);
  } catch (error) {
    Swal.fire("Fehler!", "Fehler beim Hinzufügen des Fahrzeugs!", "success");
  }
}
