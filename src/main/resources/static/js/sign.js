// addOwner
async function addOwner() {
  try {
    const response = await fetch("http://localhost:8080/api/owners", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        userName: document.getElementById('userName').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        firstName: document.getElementById('firstName').value,
        lastName: document.getElementById('lastName').value,
        dateOfBirth: document.getElementById('dateOfBirth').value,
        street: document.getElementById('street').value,
        houseNumber: document.getElementById('houseNumber').value,
        city: document.getElementById('city').value
      })
    });

    if (!response.ok) throw new Error("HTTP error " + response.status);
    const result = await response.json();
    document.querySelector("form").reset();
    alert("Registration successful!");
    console.log("Server response:", result);

  } catch (error) {
    console.error("Error adding owner:", error);
  }
}