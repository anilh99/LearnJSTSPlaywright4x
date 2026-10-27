let arr=["Anil","Sangeeta","Pramod","Ramesh"];
let score=new Array(3);
score[0]=100;
score[1]=200;
score[2]=300;
console.log(arr[0]);
console.log(arr.at(-1));
console.log(score[0]);
console.log(score.at(-1));

let numbers=new Array(1,2,3,4,5);
console.log(numbers[0]);
console.log(numbers.at(-1));
console.log(numbers.length);


let test=Array.of(10,20,30,40,50);
console.log(test[0]);
console.log(test.at(-1));
console.log(test.length);

// Array.from()
let chars = Array.from("hello");
// ["h", "e", "l", "l", "o"]
console.log(chars);

let colors=[];
colors.push("red");
colors.push("green");
colors.push("blue");
console.log(colors);

let a=[1,2,3,4,5];
let b=[...a];
console.log(b);