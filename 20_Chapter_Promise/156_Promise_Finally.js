let p=new Promise(function(resolve,reject){
    resolve("Success");
});
p.then(function(result){
    console.log(result);
}).catch(function(error){
    console.log(error);
}).finally(function(){
    console.log("Testcase completed");
});