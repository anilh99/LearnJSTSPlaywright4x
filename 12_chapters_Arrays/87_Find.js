let nums = [10,25,30,45];
let result=nums.find(nums => nums > 20);
console.log(result); // 25


let index=nums.findIndex(nums => nums > 20);
console.log(index); // 1