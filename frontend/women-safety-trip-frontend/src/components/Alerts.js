import { useState } from "react";
import axios from "axios";

function Alerts() {
  const [alerts, setAlerts] = useState([]);

  const fetchAlerts = async () => {
    try {
      const res = await axios.get("http://localhost:8080/trip/alerts");
      setAlerts(res.data);
    } catch (err) {
      alert("Error fetching alerts");
    }
  };

  return (
    <div>
      <h2>Alerts</h2>
      <button onClick={fetchAlerts}>Load Alerts</button>
      <ul>
        {alerts.map((a, i) => (
          <li key={i}>{a.message} (Trip ID: {a.tripId})</li>
        ))}
      </ul>
    </div>
  );
}

export default Alerts;
