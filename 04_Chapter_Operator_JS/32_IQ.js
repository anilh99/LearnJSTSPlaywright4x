let responseTime=850;
let sla=1000;
let message=responseTime<=sla?"Response time is within SLA":"Response time exceeds SLA";
console.log(`Response Time: ${responseTime} ms, SLA: ${sla} ms, Message: ${message}`);