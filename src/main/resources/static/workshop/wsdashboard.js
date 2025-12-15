document.addEventListener("DOMContentLoaded", async () => {
  try {
    const user = await fetch("/api/user");
    if (!user.ok) throw new Error("No user logged in yet");
    const loggedUser = await user.json();
    console.log("Workshop ID:", loggedUser.id);
    getWs(loggedUser.id);
    loadWorkshopMaintenanceTable(loggedUser.id);
  } catch (error) {
    console.error("Error loading default car:", error);
  }
});

// Load maintenance table for the workshop
async function loadWorkshopMaintenanceTable(workshopId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/maintenance/workshop/${workshopId}`
    );
    const data = await response.json();

    const tableBody = document.getElementById("wsmaintenanceTableBody");
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

// Fetch and display workshop details
async function getWs(wsId) {
  try {
    const response = await fetch(
      `http://localhost:8080/api/workshops/${wsId}`,
      {
        method: "GET",
        headers: { "Content-Type": "application/json" },
      }
    );

    if (!response.ok) throw new Error("HTTP error " + response.status);

    const ws = await response.json();

    document.getElementById("workshopName").innerText =
      ws.workshopName || "workshopName";
    document.getElementById("city").innerText =
      ws.city || "city";
  } catch (error) {
    console.error("Error fetching workshop:", error);
  }
}

