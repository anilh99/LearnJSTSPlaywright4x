console.log(greeting);
let greeting = "Hello";
console.log(greeting);


// Behind the scenes:
// var greeting;              <-- hoisted without the value and they enter temporal dead zone
// console.log(greeting);    <-- undefined
// greeting = "Hello!";      <-- assignment stays in place
// console.log(greeting);    <-- "Hello!"