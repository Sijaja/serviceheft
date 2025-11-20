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

async function addCar() {
  try {
    const response = await fetch("http://localhost:8080/api/cars", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      credentials: "include",
      body: JSON.stringify({
        photoId: 0,
        vinNumber: document.getElementById("vinNumber").value,
        carColor: "genericColor",
        manufacturer: document.getElementById("manufacturer").value,
        model: document.getElementById("model").value,
        makeYear: document.getElementById("makeYear").value,
        mileage: document.getElementById("mileage").value,
        carType: document.getElementById("carType").value,
      }),
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);
    const result = await response.json();
    document.querySelector("form").reset();
    alert("Car added successfully!");
    console.log("Server response:", result);
  } catch (error) {
    console.error("Error adding car:", error);
  }
}
