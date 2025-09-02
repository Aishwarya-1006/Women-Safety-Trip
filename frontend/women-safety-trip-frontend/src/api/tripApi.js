import axios from "axios";

const API_BASE = "http://localhost:8080/trip"; // Spring Boot backend

// ✅ Start a new trip
export const startTrip = async (userId, startLat, startLng, expectedEndTime) => {
  const payload = {
    userId,
    startLat,
    startLng,
    expectedEndTime, // must be ISO string
  };
  const response = await axios.post(`${API_BASE}/start`, payload);
  return response.data;
};
