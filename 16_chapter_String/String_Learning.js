let str="playwright";
console.log(str.length); // 10
console.log(str.at(-1)); // t
console.log(str.charAt(1)); // l
console.log(str.at(-20)); // undefined
console.log(str.charAt(20)); // ""
console.log(str.charAt(str.length-1)); // ""
console.log(str.toUpperCase()); // PLAYWRIGHT

let s1="Hello World";
console.log(s1.split('').sort().join('')); // " HWdellloor"