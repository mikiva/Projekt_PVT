

var dots = window.setInterval( function() {
    var wait = document.getElementById("wait");
    
    if (wait == null)
    return;
    
    if ( wait.innerHTML.length > 3 ) 
        wait.innerHTML = "";
    else 
        wait.innerHTML += ".";
    



}, 100);