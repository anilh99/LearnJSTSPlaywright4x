let num=[5,4,3,2,1];
num.sort((a,b)=>a-b);
console.log(num); // [ 1, 2, 3, 4, 5 ]

let score=[1,2,3,4,5];
score.sort((a,b)=>b-a);
console.log(score); // [ 5, 4, 3, 2, 1 ]


let fruits=["Banana","Apple","Mango"];
fruits.sort((a,b)=>a.localeCompare(b));
console.log(fruits); // [ 'Apple', 'Banana', 'Mango' ]