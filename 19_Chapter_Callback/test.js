function PrepareFood(callback) {
    console.log("Prepare Food");
    callback();
}
function eatFood() {
    console.log("Eat Food");
}
PrepareFood(eatFood);