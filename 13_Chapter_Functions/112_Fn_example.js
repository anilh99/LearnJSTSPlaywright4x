function validateStatusCheck(num){
    if(num>=200&&num<=300){
        console.log("Request is successful");
    }
}
const validateStatusCheckFunction=function(num){
    if(num>=200&&num<=300){
        console.log("Request is successful");
    }
}
const validateStatusCheckArrow=(num)=>{
    if(num>=200&&num<=300){
        console.log("Request is successful");
    }
}
validateStatusCheck(200); // Request is successful
validateStatusCheckFunction(200); // Request is successful
validateStatusCheckArrow(200); // Request is successful