import { MapContainer, TileLayer, Marker, useMapEvents } from "react-leaflet";
import { useState } from "react";

function MapPicker({ setStartLocation, setDropLocation }) {
  const [start, setStart] = useState(null);
  const [drop, setDrop] = useState(null);

  function MapClickHandler() {
    useMapEvents({
      click(e) {
        if (!start) {
          setStart(e.latlng);
          setStartLocation(e.latlng); // send to parent
          console.log("✅ Start selected:", e.latlng);
        } else if (!drop) {
          setDrop(e.latlng);
          setDropLocation(e.latlng); // send to parent
          console.log("✅ Drop selected:", e.latlng);
        }
      },
    });
    return null;
  }

  return (
    <MapContainer
      center={[13.0827, 80.2707]} // Chennai default
      zoom={13}
      style={{ height: "400px", width: "100%" }}
      scrollWheelZoom={true} // allow zoom manually
      doubleClickZoom={false} // disable auto zoom on dbl click
      dragging={true} // allow drag
    >
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      <MapClickHandler />
      {start && <Marker position={start} />}
      {drop && <Marker position={drop} />}
    </MapContainer>
  );
}

export default MapPicker;
