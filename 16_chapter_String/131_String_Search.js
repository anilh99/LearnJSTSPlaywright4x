let url = "https://staging.vwo.com/api/login?retry=true";
// includes()
url.includes("staging");  
url.includes("production");
console.log(url.includes("staging")); // true
console.log(url.includes("production")); // false
console.log(url.startsWith("https://staging")); // true
console.log(url.endsWith("retry=true")); // true

console.log(url.indexOf("a")); // 8
console.log(url.lastIndexOf("a")); // 30
console.log(url.indexOf("nothere"));