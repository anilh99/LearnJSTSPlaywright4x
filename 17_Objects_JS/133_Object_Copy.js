let a={status:"Pass"};
let b=a;
b.status="Fail";
console.log(a.status);

let c={status:"Pass"};
let d={...c};
d.status="Fail";
console.log(c.status);