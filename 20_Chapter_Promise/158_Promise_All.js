let checkAuth=Promise.resolve("User is authenticated");
let checkDB=Promise.resolve ("DB is up");
let checkCache=Promise.resolve("Cache is up");

Promise.all([checkAuth,checkDB,checkCache]).then(function(result){
    console.log("All checks passed:", result);
});