document.addEventListener("DOMContentLoaded", async () => {
  try {
    console.log("History page loaded");
    const user = await fetch("/api/user");
    if (!user.ok) throw new Error("No user logged in yet");
    const loggedUser = await user.json();
    console.log("Logged user:", loggedUser);
    const owner = await fetch(`/api/owners/${loggedUser.id}`);
    if (!owner.ok) throw new Error("No owner found");
    const ownerData = await owner.json();
    console.log("Owner data:", ownerData);
    const defaultCarId = ownerData.defaultCarId;
    console.log("Default car ID:", defaultCarId);

    document.getElementById("userName").innerText = loggedUser.email || "User";
    document.getElementById("ownerName").innerText = ownerData.firstName + " " + ownerData.lastName || "Owner";

    loadMaintenanceHistory(defaultCarId);
  } catch (error) {
    console.error("Error loading default car:", error);
  }
});

async function loadMaintenanceHistory(carId) {
  try {
    console.log("Loading maintenance history for car:", carId);
    const response = await fetch(`/api/maintenance/table/${carId}`);

    console.log("Maintenance table response:", response);
    if (!response.ok) throw new Error("HTTP error " + response.status);

    const maintenanceData = await response.json();
    console.log("Maintenance data received:", maintenanceData);

    if (!maintenanceData || maintenanceData.length === 0) {
      console.log("No maintenance data found");
      document.getElementById("maintenanceHistoryContainer").innerHTML = `
        <div class="col-12">
          <div class="card bg-white rounded-10 border border-white p-4 mb-4">
            <div class="text-center">
              <i class="material-symbols-outlined" style="font-size: 48px; color: #ccc;">history</i>
              <h4 class="mt-3">Keine Wartungseinträge gefunden</h4>
              <p class="text-muted">Es wurden noch keine Wartungen für dieses Fahrzeug erfasst.</p>
            </div>
          </div>
        </div>
      `;
      return;
    }

    // Sort by date (newest first)
    maintenanceData.sort((a, b) => new Date(b.date) - new Date(a.date));

    const container = document.getElementById("maintenanceHistoryContainer");
    container.innerHTML = "";
    console.log("Creating cards for", maintenanceData.length, "maintenance entries");

    for (const maintenance of maintenanceData) {
      console.log("Fetching details for maintenance:", maintenance.mtncId);
      // Fetch full maintenance details
      const detailResponse = await fetch(`/api/maintenance/${maintenance.mtncId}`);

      console.log("Detail response:", detailResponse);
      if (!detailResponse.ok) {
        console.error("Failed to fetch details for maintenance:", maintenance.mtncId);
        continue;
      }

      const details = await detailResponse.json();
      console.log("Maintenance details:", details);

      const card = createMaintenanceCard(details);
      console.log("Card created:", card);
      container.appendChild(card);
    }
    console.log("All cards added to container");
  } catch (error) {
    console.error("Error fetching maintenance history:", error);
    document.getElementById("maintenanceHistoryContainer").innerHTML = `
      <div class="col-12">
        <div class="card bg-white rounded-10 border border-white p-4 mb-4">
          <div class="text-center">
            <i class="material-symbols-outlined" style="font-size: 48px; color: #f44336;">error</i>
            <h4 class="mt-3">Fehler beim Laden der Wartungsdaten</h4>
            <p class="text-muted">${error.message}</p>
          </div>
        </div>
      </div>
    `;
  }
}

function createMaintenanceCard(maintenance) {
  const col = document.createElement("div");
  col.className = "col-12";

  const formattedDate = new Date(maintenance.mtncDate).toLocaleDateString("de-DE", {
    year: "numeric",
    month: "long",
    day: "numeric",
  });

  const formattedCost = maintenance.cost.toFixed(2);

  // Determine card color based on maintenance type
  let cardColor = "#1d546c";
  switch (maintenance.mtncType) {
    case "INSPECTION":
      cardColor = "#1d546c";
      break;
    case "REPAIR":
      cardColor = "#c45850";
      break;
    case "SERVICE":
      cardColor = "#4CAF50";
      break;
    case "OILCHANGE":
      cardColor = "#FF9800";
      break;
    default:
      cardColor = "#1d546c";
  }

  col.innerHTML = `
    <div class="card rounded-10 border-0 mb-4" style="background: linear-gradient(101deg, ${cardColor} 55.73%, ${adjustColor(cardColor, -20)} 99.52%);">
      <div class="card-body p-4">
        <div class="d-flex justify-content-between align-items-start mb-3">
          <div>
            <h4 class="text-white mb-1">${getMaintenanceTypeText(maintenance.mtncType)}</h4>
            <p class="mb-0" style="color: #cbc7ff; font-size: 14px;">
              <i class="material-symbols-outlined" style="font-size: 16px; vertical-align: middle;">calendar_today</i>
              ${formattedDate}
            </p>
          </div>
          <div class="text-end">
            <h3 class="text-white mb-0">${formattedCost} €</h3>
            <p class="mb-0" style="color: #cbc7ff; font-size: 14px;">Gesamtkosten</p>
          </div>
        </div>

        <div class="row g-2 mb-3">
          <div class="col-6">
            <div class="p-2 rounded" style="background: rgba(255, 255, 255, 0.1);">
              <div class="d-flex align-items-center">
                <i class="material-symbols-outlined text-white me-2" style="font-size: 20px;">speed</i>
                <div>
                  <p class="mb-0" style="color: #cbc7ff; font-size: 12px;">Kilometerstand</p>
                  <p class="mb-0 text-white fw-medium">${maintenance.currentMileage.toLocaleString()} km</p>
                </div>
              </div>
            </div>
          </div>
          <div class="col-6">
            <div class="p-2 rounded" style="background: rgba(255, 255, 255, 0.1);">
              <div class="d-flex align-items-center">
                <i class="material-symbols-outlined text-white me-2" style="font-size: 20px;">build</i>
                <div>
                  <p class="mb-0" style="color: #cbc7ff; font-size: 12px;">Werkstatt</p>
                  <p class="mb-0 text-white fw-medium">${maintenance.workshop.companyName}</p>
                </div>
              </div>
            </div>
          </div>
        </div>

        ${maintenance.inspectionNotes ? `
        <div class="mb-3 p-2 rounded" style="background: rgba(255, 255, 255, 0.1);">
          <p class="mb-1" style="color: #cbc7ff; font-size: 12px;">Notizen</p>
          <p class="mb-0 text-white">${maintenance.inspectionNotes}</p>
        </div>
        ` : ''}

        ${createCostBreakdown(maintenance.costs)}

        <button class="btn btn-sm mt-3 w-100" style="background: rgba(255, 255, 255, 0.2); color: white; border: 1px solid rgba(255, 255, 255, 0.3);"
                onclick="toggleDetails(${maintenance.mtncId})">
          <i class="material-symbols-outlined" style="font-size: 16px; vertical-align: middle;">expand_more</i>
          Details anzeigen
        </button>

        <div id="details-${maintenance.mtncId}" class="mt-3" style="display: none;">
          ${createDetailedChecks(maintenance)}
        </div>
      </div>
    </div>
  `;

  return col;
}

function createCostBreakdown(costs) {
  if (!costs) return '';

  const costItems = [
    { label: 'Motor', value: costs.engineCost },
    { label: 'Riemen & Schläuche', value: costs.beltsHosesCost },
    { label: 'Bremsen', value: costs.brakesCost },
    { label: 'Reifen', value: costs.tiresCost },
    { label: 'Elektrik', value: costs.electricCost },
    { label: 'Karosserie', value: costs.bodyPartsCost },
    { label: 'Filter', value: costs.filtersCost },
    { label: 'Abgas', value: costs.exhaustCost },
    { label: 'HVAC', value: costs.hvacCost },
    { label: 'Rost', value: costs.rostCost },
  ];

  const nonZeroCosts = costItems.filter(item => item.value > 0);

  if (nonZeroCosts.length === 0) return '';

  return `
    <div class="mt-3">
      <p class="mb-2 text-white fw-medium">Kostenaufschlüsselung</p>
      <div class="row g-2">
        ${nonZeroCosts.map(item => `
          <div class="col-6">
            <div class="p-2 rounded" style="background: rgba(255, 255, 255, 0.1);">
              <p class="mb-0" style="color: #cbc7ff; font-size: 11px;">${item.label}</p>
              <p class="mb-0 text-white fw-medium">${item.value.toFixed(2)} €</p>
            </div>
          </div>
        `).join('')}
      </div>
    </div>
  `;
}

function createDetailedChecks(maintenance) {
  let html = '<div class="row g-2">';

  // Engine Check
  if (maintenance.engineCheck) {
    html += `
      <div class="col-12">
        <div class="p-3 rounded" style="background: rgba(255, 255, 255, 0.15);">
          <h6 class="text-white mb-2"><i class="material-symbols-outlined" style="font-size: 18px; vertical-align: middle;">settings</i> Motorprüfung</h6>
          <div class="row g-2">
            ${createCheckItem('Motorstatus', maintenance.engineCheck.engineStatus)}
            ${createCheckItem('Ölstand', maintenance.engineCheck.oilLevel)}
            ${createCheckItem('Ölzustand', maintenance.engineCheck.oilCondition)}
            ${maintenance.engineCheck.oilReplaced ? '<div class="col-6"><p class="mb-0 text-white">✓ Öl gewechselt</p></div>' : ''}
            ${createCheckItem('Kühlmittelstand', maintenance.engineCheck.coolantLevel)}
            ${createCheckItem('Bremsflüssigkeit', maintenance.engineCheck.brakeFluidLevel)}
          </div>
        </div>
      </div>
    `;
  }

  // Brake Check
  if (maintenance.brakeCheck) {
    html += `
      <div class="col-12">
        <div class="p-3 rounded" style="background: rgba(255, 255, 255, 0.15);">
          <h6 class="text-white mb-2"><i class="ri-alert-line" style="font-size: 18px; vertical-align: middle;"></i> Bremsenprüfung</h6>
          <div class="row g-2">
            ${maintenance.brakeCheck.fPadThickness ? `<div class="col-6"><p class="mb-0" style="color: #cbc7ff; font-size: 12px;">Vorne Beläge</p><p class="mb-0 text-white">${maintenance.brakeCheck.fPadThickness} mm</p></div>` : ''}
            ${maintenance.brakeCheck.rPadThickness ? `<div class="col-6"><p class="mb-0" style="color: #cbc7ff; font-size: 12px;">Hinten Beläge</p><p class="mb-0 text-white">${maintenance.brakeCheck.rPadThickness} mm</p></div>` : ''}
            ${createCheckItem('Vordere Scheiben', maintenance.brakeCheck.frontRotorsCon)}
            ${createCheckItem('Hintere Scheiben', maintenance.brakeCheck.rearRotorsCon)}
            ${createCheckItem('Bremsleitungen', maintenance.brakeCheck.brakeLines)}
          </div>
        </div>
      </div>
    `;
  }

  // Tire Check
  if (maintenance.tireCheck) {
    html += `
      <div class="col-12">
        <div class="p-3 rounded" style="background: rgba(255, 255, 255, 0.15);">
          <h6 class="text-white mb-2"><i class="material-symbols-outlined" style="font-size: 18px; vertical-align: middle;">tire_repair</i> Reifenprüfung</h6>
          <div class="row g-2">
            ${maintenance.tireCheck.treadFrontLeft ? `<div class="col-6"><p class="mb-0" style="color: #cbc7ff; font-size: 12px;">VL Profil</p><p class="mb-0 text-white">${maintenance.tireCheck.treadFrontLeft} mm</p></div>` : ''}
            ${maintenance.tireCheck.treadFrontRight ? `<div class="col-6"><p class="mb-0" style="color: #cbc7ff; font-size: 12px;">VR Profil</p><p class="mb-0 text-white">${maintenance.tireCheck.treadFrontRight} mm</p></div>` : ''}
            ${maintenance.tireCheck.treadRearLeft ? `<div class="col-6"><p class="mb-0" style="color: #cbc7ff; font-size: 12px;">HL Profil</p><p class="mb-0 text-white">${maintenance.tireCheck.treadRearLeft} mm</p></div>` : ''}
            ${maintenance.tireCheck.treadRearRight ? `<div class="col-6"><p class="mb-0" style="color: #cbc7ff; font-size: 12px;">HR Profil</p><p class="mb-0 text-white">${maintenance.tireCheck.treadRearRight} mm</p></div>` : ''}
            ${createCheckItem('Stoßdämpfer', maintenance.tireCheck.shockAbsorbers)}
          </div>
        </div>
      </div>
    `;
  }

  // Belt/Hose Check
  if (maintenance.beltHoseCheck) {
    html += `
      <div class="col-12">
        <div class="p-3 rounded" style="background: rgba(255, 255, 255, 0.15);">
          <h6 class="text-white mb-2"><i class="material-symbols-outlined" style="font-size: 18px; vertical-align: middle;">category</i> Riemen & Schläuche</h6>
          <div class="row g-2">
            ${createCheckItem('Keilriemen', maintenance.beltHoseCheck.serpentineBelt)}
            ${createCheckItem('Zahnriemen', maintenance.beltHoseCheck.timingBelt)}
            ${createCheckItem('Kühlerschläuche', maintenance.beltHoseCheck.radiatorHoses)}
            ${createCheckItem('Heizungsschläuche', maintenance.beltHoseCheck.heaterHoses)}
          </div>
        </div>
      </div>
    `;
  }

  html += '</div>';
  return html;
}

function createCheckItem(label, value) {
  if (!value) return '';
  return `
    <div class="col-6">
      <p class="mb-0" style="color: #cbc7ff; font-size: 12px;">${label}</p>
      <p class="mb-0 text-white">${formatCheckValue(value)}</p>
    </div>
  `;
}

function formatCheckValue(value) {
  if (typeof value === 'string') {
    // Convert enum values to readable German text
    const translations = {
      'OPTIMAL': 'Optimal',
      'FAIR': 'Ausreichend',
      'POOR': 'Mangelhaft',
      'REPLACED': 'Ersetzt',
      'NOT_CHECKED': 'Nicht geprüft',
      'HIGH': 'Hoch',
      'LOW': 'Niedrig',
      'GOOD': 'Gut',
      'OKAY': 'OK',
      'TOREPLACE': 'Zu ersetzen'
    };
    return translations[value] || value;
  }
  return value;
}

function getMaintenanceTypeText(type) {
  const types = {
    'INSPECTION': 'Inspektion',
    'REPAIR': 'Reparatur',
    'SERVICE': 'Service',
    'OILCHANGE': 'Ölwechsel',
  };
  return types[type] || type;
}

function adjustColor(color, percent) {
  const num = parseInt(color.replace("#",""), 16);
  const amt = Math.round(2.55 * percent);
  const R = (num >> 16) + amt;
  const G = (num >> 8 & 0x00FF) + amt;
  const B = (num & 0x0000FF) + amt;
  return "#" + (0x1000000 + (R<255?R<1?0:R:255)*0x10000 + (G<255?G<1?0:G:255)*0x100 + (B<255?B<1?0:B:255)).toString(16).slice(1);
}

function toggleDetails(mtncId) {
  const detailsDiv = document.getElementById(`details-${mtncId}`);
  const button = event.target.closest('button');
  const icon = button.querySelector('.material-symbols-outlined');

  if (detailsDiv.style.display === 'none') {
    detailsDiv.style.display = 'block';
    icon.textContent = 'expand_less';
    button.innerHTML = `<i class="material-symbols-outlined" style="font-size: 16px; vertical-align: middle;">expand_less</i> Details ausblenden`;
  } else {
    detailsDiv.style.display = 'none';
    icon.textContent = 'expand_more';
    button.innerHTML = `<i class="material-symbols-outlined" style="font-size: 16px; vertical-align: middle;">expand_more</i> Details anzeigen`;
  }
}
