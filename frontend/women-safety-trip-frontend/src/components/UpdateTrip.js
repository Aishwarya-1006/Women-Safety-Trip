import { useState } from "react";
import axios from "axios";

function UpdateTrip() {
  const [tripId, setTripId] = useState("");
  const [lat, setLat] = useState("");
  const [lng, setLng] = useState("");

  const handleUpdate = async () => {
    try {
      await axios.post("http://localhost:8080/trip/update", {
        tripId,
        latitude: lat,
        longitude: lng
      });
      alert("Location updated!");
    } catch (err) {
      alert("Error updating location");
    }
  };

  return (
    <div>
      <h2>Update Trip</h2>
      <input placeholder="Trip ID" value={tripId} onChange={e => setTripId(e.target.value)} />
      <input placeholder="Latitude" value={lat} onChange={e => setLat(e.target.value)} />
      <input placeholder="Longitude" value={lng} onChange={e => setLng(e.target.value)} />
      <button onClick={handleUpdate}>Update Location</button>
    </div>
  );
}

export default UpdateTrip;
