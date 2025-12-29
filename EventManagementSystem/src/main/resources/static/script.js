const API_URL = "http://localhost:8080/event/v1";

// LOAD ALL EVENTS
async function loadEvents() {
    const res = await fetch(`${API_URL}/getAll`);
    const events = await res.json();
    eventBody.innerHTML = events.map(e => `
        <tr>
            <td>${e.id}</td>
            <td>${e.eventName}</td>
            <td>${e.eventType}</td>
            <td>${e.location}</td>
            <td>${e.ticketPrice}</td>
            <td>
                <button class="btn btn-edit" onclick="editEvent(${e.id})">Edit</button>
                <button class="btn btn-delete" onclick="deleteEvent(${e.id})">Delete</button>
            </td>
        </tr>
    `).join("");
}

// EDIT
function editEvent(id) {
    window.location.href = `add-event.html?edit=${id}`;
}

// PREFILL FORM
async function prefillForm(id) {
    const res = await fetch(`${API_URL}/get/${id}`);
    const e = await res.json();

    document.getElementById("id").value = e.id;
    document.getElementById("id").disabled = true;
    eventName.value = e.eventName;
    eventType.value = e.eventType;
    location.value = e.location;
    organizer.value = e.organizer;
    ticketPrice.value = e.ticketPrice;
}

// SAVE / UPDATE
async function saveEvent(data, editId) {
    const url = editId ? `${API_URL}/update/${editId}` : `${API_URL}/add`;
    const method = editId ? "PUT" : "POST";

    const res = await fetch(url, {
        method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data)
    });
    return res.ok;
}

// DELETE
async function deleteEvent(id) {
    if (!confirm("Delete this event?")) return;
    await fetch(`${API_URL}/delete/${id}`, { method: "DELETE" });
    loadEvents();
}
