let a = [1, 2];
let b = [3, 4];
let c=a.concat(b);
console.log(c); // [ 1, 2, 3, 4 ]

let d=[...a,...b];
console.log(d); // [ 1, 2, 3, 4 ]