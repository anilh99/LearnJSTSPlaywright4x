let matrix=[["login","pass",200],["login","fail",401],["login","pass",200]];
console.log(matrix[0][0]);
for(let i=0;i<matrix.length;i++){
    for(let j=0;j<matrix[i].length;j++){
        process.stdout.write(matrix[i][j]+" ");
    }
    console.log("");
}