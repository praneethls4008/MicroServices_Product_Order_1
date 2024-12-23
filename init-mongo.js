db.createUser({
    user: "productAdmin",
    pwd: "passwordAdmin",
    roles: [
        {
            role: "readWrite",
            db: "product-service"
        }
    ]
});