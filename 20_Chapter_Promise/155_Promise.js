let res=new Promise(function(resolve,reject){
    resolve({status:200,message:'Success'});
});
res.then(function(result){
    console.log(result);
});

let fail=new Promise(function(resolve,reject){
    reject("Failed");
});
fail.catch(function(error){
    console.log(error);
});