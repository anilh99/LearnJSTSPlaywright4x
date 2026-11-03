let scores=[[85,90,78],[88,76,92],[95,89,84]];
let rowSum=scores.map(row=>row.reduce((a,b)=>a+b));
console.log(rowSum); // [253, 256, 268]