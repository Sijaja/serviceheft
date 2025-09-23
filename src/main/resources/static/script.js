async function fetchData() {
  const response = await fetch("https://your-service-name.onrender.com/api/endpoint");
  const data = await response.json();
  document.getElementById("output").textContent = JSON.stringify(data, null, 2);
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