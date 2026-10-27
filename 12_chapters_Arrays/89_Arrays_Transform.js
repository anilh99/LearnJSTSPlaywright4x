let scores = [45, 82, 91, 60, 73];
let grades=scores.map(score=>score>=90?"A":score>=80?"B":score>=70?"C":"D");
console.log(grades); // [ 'D', 'B', 'A', 'D', 'C' ]


// Filter
let passing = scores.filter(s => s >= 70);
console.log(passing);