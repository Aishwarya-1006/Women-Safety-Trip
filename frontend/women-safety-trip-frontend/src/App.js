import { useState } from "react";
import MapPicker from "./MapPicker";
import TripStarter from "./components/TripStarter";

function App() {
  const [startLocation, setStartLocation] = useState(null);
  const [dropLocation, setDropLocation] = useState(null);

  return (
    <div>
      <h1>Women Safety App</h1>
      <MapPicker
        setStartLocation={setStartLocation}
        setDropLocation={setDropLocation}
      />
      <TripStarter startLocation={startLocation} dropLocation={dropLocation} />
    </div>
  );
}

export default App;
