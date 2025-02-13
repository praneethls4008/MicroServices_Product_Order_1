import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
    realm: import.meta.env.KEYCLOAK_REALM,
    url: import.meta.env.KEYCLOAK_URL,
    clientId: import.meta.env.KEYCLOAK_CLIENT_ID
})

export default keycloak;