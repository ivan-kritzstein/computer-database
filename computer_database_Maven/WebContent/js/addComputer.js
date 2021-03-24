/**
 * 
 */

function minDiscontinued(value) {
   let input = document.getElementsByName("discontinued");
   input[0].setAttribute("min", value);
}

 function maxIntroduced(value) {
   let input = document.getElementsByName("introduced");
   input[0].setAttribute("max", value);
}


