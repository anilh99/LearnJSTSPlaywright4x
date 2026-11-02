let str="programming";
let duplicate="";
for(let i=0;i<str.length;i++){
    if(duplicate.includes(str[i])){
        continue;
    }
    duplicate+=str[i];
}
console.log(duplicate); // "progamin"