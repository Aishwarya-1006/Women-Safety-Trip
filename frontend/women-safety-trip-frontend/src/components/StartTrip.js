import { useState } from "react";
import axios from "axios";
import MapPicker from "./MapPicker";

function StartTrip() {
  const [source, setSource] = useState(null);
  const [destination, setDestination] = useState(null);
  const [time, setTime] = useState("");

  const handleStart = async () => {
    try {
      const res = await axios.post("http://localhost:8080/trip/start", {
        source,
        destination,
        expectedTime: time
      });
      alert("Trip started! ID: " + res.data.tripId);
    } catch (err) {
      alert("Error starting trip");
    }
  };

  return (
    <div>
      <h2>Start Trip</h2>

      <p><b>Pick Source</b></p>
      <MapPicker setLocation={setSource} />

      <p><b>Pick Destination</b></p>
      <MapPicker setLocation={setDestination} />

      <input
        placeholder="Expected Time (minutes)"
        value={time}
        onChange={(e) => setTime(e.target.value)}
      />
      <button onClick={handleStart}>Start</button>
    </div>
  );
}

export default StartTrip;
