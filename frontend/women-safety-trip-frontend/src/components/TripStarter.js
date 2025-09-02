// src/components/TripStarter.js
import { useState } from "react";
import axios from "axios";

function TripStarter({ startLocation, dropLocation }) {
  const [trip, setTrip] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleStartTrip = async () => {
    if (!startLocation || !dropLocation) {
      alert("Please select both start and drop locations.");
      return;
    }

    try {
      setLoading(true);
      const res = await axios.post("http://localhost:8080/trip/start", {
        userId: "user123", // TODO: Replace with real logged-in user
        startLat: startLocation.lat,
        startLng: startLocation.lng,
        expectedEndTime: new Date(Date.now() + 30 * 60 * 1000).toISOString(), // +30 min
      });

      setTrip(res.data);
      console.log("Trip started:", res.data);
    } catch (err) {
      console.error("Failed to start trip", err);
    } finally {
      setLoading(false);
    }
  };

  const handleEndTrip = async () => {
    if (!trip?.tripId) {
      alert("No active trip found");
      return;
    }

    try {
      const res = await axios.put(
        `http://localhost:8080/trip/end/${trip.tripId}`
      );
      setTrip(res.data);
      console.log("Trip ended:", res.data);
    } catch (err) {
      console.error("Failed to end trip", err);
    }
  };

  const handleSOS = async () => {
    if (!trip?.tripId) {
      alert("No active trip found");
      return;
    }

    try {
      const res = await axios.post(
        `http://localhost:8080/trip/${trip.tripId}/sos`,
        { message: "Help! Emergency situation" }
      );
      setTrip(res.data);
      console.log("SOS triggered:", res.data);
    } catch (err) {
      console.error("Failed to trigger SOS", err);
    }
  };

  return (
    <div style={{ marginTop: "1rem" }}>
      <button onClick={handleStartTrip} disabled={loading || trip}>
        🚀 Start Trip
      </button>
      <button onClick={handleEndTrip} disabled={!trip}>
        🛑 End Trip
      </button>
      <button onClick={handleSOS} disabled={!trip}>
        ⚠️ SOS
      </button>

      {trip && (
        <div style={{ marginTop: "1rem" }}>
          <h3>Active Trip</h3>
          <p>
            <b>ID:</b> {trip.tripId}
          </p>
          <p>
            <b>Status:</b> {trip.status}
          </p>
          <p>
            <b>Current Location:</b>{" "}
            {trip.currentLat}, {trip.currentLng}
          </p>
        </div>
      )}
    </div>
  );
}

export default TripStarter;
