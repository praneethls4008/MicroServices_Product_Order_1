import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    realm: "spring-microservices1-security-realm",
    url: "http://localhost:8181",
    clientId: "frontend-react-client"
})

export default keycloak;