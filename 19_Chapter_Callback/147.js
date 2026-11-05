function placeOrder(item,callback){
console.log("place order");
console.log("Order placqed for item: "+item);
callback();
}

//1st way Normal function
// function processOrder(){
// console.log("process order");
// }
// placeOrder("Pizza",processOrder);

//2nd way anonymous function
// placeOrder("Burger",function(){
//     console.log("process order");
// });
//3rd way arrow function
placeOrder("Pizza",()=>{
    console.log("process order");
});