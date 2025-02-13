import keycloak from "../keycloak";
import axios from "axios";

const api = axios.create({
  baseURL: import.meta.env.BACKEND_URL,
});

api.interceptors.request.use(async (config) => {
  if (keycloak.authenticated) {
    try {
      // Refresh token if it expires within 30 second
      await keycloak.updateToken(30);
      config.headers.Authorization = `Bearer ${keycloak.token}`;
    } catch (error) {
      console.log(`failed to refresh token ${error}`);
      keycloak.login();
    }
  }
  return config;
});

export default api;
